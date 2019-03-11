package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.HostMapper;
import app.withyou.ahometoshare.dao.PropertyMapper;
import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.HostDetail;
import app.withyou.ahometoshare.model.Property;
import app.withyou.ahometoshare.service.HostService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);

    @Autowired
    private HostMapper hostMapper;


    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<Host> getAllHosts() {
        List<Host> hosts = hostMapper.selectAll();
        hosts.stream().forEach(host -> host.setPassword(""));
        return hosts;
    }

    @Override
    public int insertHost(Host host) {
        Md5Hash password = new Md5Hash( host.getPassword(), host.getEmail());
        host.setPassword(password.toString());
        return hostMapper.insert(host);
    }

    @Override
    public Host selectHostByEmail(String email) {
        return hostMapper.selectByEmail(email);
    }

    @Override
    public boolean updateHost(Host host) {
        try{
            hostMapper.updateByPrimaryKeySelective(host);
            return true;
        }catch (RuntimeException e){
            logger.error("Fail to update host",e);
            return false;
        }
    }

    @Override
    public boolean updateProperties(List<Property> list){
        try {
            list.forEach(property -> propertyMapper.updateByPrimaryKeySelective(property));
        }catch (RuntimeException e){
            logger.error("Fail to update Property",e);
            return false;
        }
        return true;
    }

    @Override
    public HostDetail getHostDetailById(Integer id) {
        HostDetail hostDetail = new HostDetail();
        Host host = hostMapper.selectByPrimaryKey(id);
        host.setPassword("");
        List<Property> propertyList = propertyMapper.getPropertyListByHostId(host.getHostId());
        hostDetail.setHost(host);
        hostDetail.setPropertyList(propertyList);
        return hostDetail;
    }
}
