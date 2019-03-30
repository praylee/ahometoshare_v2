package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.*;
import app.withyou.ahometoshare.model.*;
import app.withyou.ahometoshare.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private HostMapper hostMapper;

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyPictureMapper propertyPictureMapper;

    @Autowired
    private RenterMapper renterMapper;

    @Autowired
    private LoginRecordMapper loginRecordMapper;

    @Override
    public Admin selectAdminByUsername(String username) {
        return adminMapper.selectByUsername(username);
    }

    @Override
    public boolean deleteHostByAdmin(Integer hostId) {
        Host host = hostMapper.selectByPrimaryKey(hostId);
        try{
            if (host ==null){
                return false;
            }
            for (Property property : propertyMapper.getPropertyListByHostId(host.getHostId())){
                for(PropertyPicture pp : propertyPictureMapper.selectByPropertyId(property.getPropertyId())){
                    propertyPictureMapper.deleteByPrimaryKey(pp.getPictureId());//delete all images first
                }
                propertyMapper.deleteByPrimaryKey(property.getPropertyId());//delete properties
            }
            int result = hostMapper.deleteByPrimaryKey(host.getHostId());//delete host
            if(result==1){
                return true;
            }
            return false;
        }catch (Exception e){
            logger.error("Failed to delete Host by Admin", e);
            return false;
        }
    }

    @Override
    public boolean deleteRenterByAdmin(Integer renterId) {
        Renter renter = renterMapper.selectByPrimaryKey(renterId);
        if (renter == null){
            return false;
        }
        try{
            int result = renterMapper.deleteByPrimaryKey(renterId);
            if(result == 1){
                return true;
            }
            return false;
        }catch (Exception e){
            logger.error("Failed to delete Renter by Admin", e);
            return false;
        }
    }

    @Override
    public Map<String, Long> getReferralSourceAggregation() {
        List<HashMap> countHost =  hostMapper.countHostGroupByReferralSource();
        List<HashMap> countRenter =  renterMapper.countRenterGroupByReferralSource();
        Map<String, Long> referralSource = new HashMap<>();
        for(HashMap hashMap : countHost){
            String key = (String)hashMap.get("source");
            Long value = (Long)hashMap.get("num");
            referralSource.put(key, value);
        }
        for(HashMap hashMap : countRenter){
            String key = (String)hashMap.get("source");
            Long value = null;
            if(referralSource.keySet().contains(key)){
                value = (Long)hashMap.get("num")+(Long)referralSource.get(key);
            }else {
                value = (Long)hashMap.get("num");
            }
            referralSource.put(key, value);
        }
        logger.debug("referralSource"+ referralSource.entrySet());
        return referralSource;
    }

    @Override
    public Map<String, Long> selectLoginHostRecordGroupByMonth() {
        List<Map> hostLoginRecordByMonth = loginRecordMapper.selectHostLoginRecordGroupByMonth();
        Map<String, Long> hostLoginRecords = new HashMap<>();
        for(Map map : hostLoginRecordByMonth){
            hostLoginRecords.put((String)map.get("time"), (Long)map.get("num"));
        }
        logger.debug("selectLoginHostRecordGroupByMonth"+ hostLoginRecords);
        return hostLoginRecords;
    }

    @Override
    public Map<String, Long> selectLoginRenterRecordGroupByMonth() {
        List<Map> renterLoginRecordByMonth = loginRecordMapper.selectRenterLoginRecordGroupByMonth();
        Map<String, Long> renterLoginRecords = new HashMap<>();
        for(Map map : renterLoginRecordByMonth){
            renterLoginRecords.put((String)map.get("time"), (Long)map.get("num"));
        }
        logger.debug("selectLoginRenterRecordGroupByMonth"+ renterLoginRecords);
        return renterLoginRecords;
    }

    @Override
    public Map<String, Long> selectHostAggregatedData() {
        Map<String, Long> hostAggregatedData = new HashMap<>();
        Long weeklyActiveHost = loginRecordMapper.countActiveHosts(7);
        Long monthlyActiveHost = loginRecordMapper.countActiveHosts(30);
        Long quarterlyActiveHost = loginRecordMapper.countActiveHosts(90);
        Long totalRegisteredHost = hostMapper.countHosts();
        hostAggregatedData.put("weeklyActiveHost",weeklyActiveHost);
        hostAggregatedData.put("monthlyActiveHost",monthlyActiveHost);
        hostAggregatedData.put("quarterlyActiveHost",quarterlyActiveHost);
        hostAggregatedData.put("totalRegisteredHost",totalRegisteredHost);
        return hostAggregatedData;
    }

    @Override
    public Map<String, Long> selectRenterAggregatedData() {
        Long weeklyActiveRenter = loginRecordMapper.countActiveRenters(7);
        Long monthlyActiveRenter = loginRecordMapper.countActiveRenters(30);
        Long quarterlyActiveRenter = loginRecordMapper.countActiveRenters(90);
        Long totalRegisteredRenter = renterMapper.countRenters();
        Map<String, Long> renterAggregatedData = new HashMap<>();
        renterAggregatedData.put("weeklyActiveRenter", weeklyActiveRenter);
        renterAggregatedData.put("monthlyActiveRenter", monthlyActiveRenter);
        renterAggregatedData.put("quarterlyActiveRenter", quarterlyActiveRenter);
        renterAggregatedData.put("totalRegisteredRenter", totalRegisteredRenter);
        return renterAggregatedData;
    }


}
