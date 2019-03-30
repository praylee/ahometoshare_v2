package app.withyou.ahometoshare.controller;


import app.withyou.ahometoshare.model.*;
import app.withyou.ahometoshare.service.AdminService;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.RenterService;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import app.withyou.ahometoshare.utils.EncryptionUtil;
import app.withyou.ahometoshare.utils.RestJson;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class AdminRestController {

    private static Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    HostService hostService;

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    RenterService renterService;


    @RequestMapping(value = "/admin/getAllHosts" ,method = RequestMethod.GET)
    public String getAllHosts(){
        RestJson restJson = new RestJson();
        List<Host> hosts= hostService.getAllHosts();
        restJson.setData(hosts);
        return JSONObject.toJSONString(restJson);
    }


    @GetMapping(value = {"/admin/getAllRenters"})
    public String getAllRenters(){
        RestJson restJson = new RestJson();
        List<Renter> renters = renterService.getAllRenters();
        restJson.setData(renters);
        return JSONObject.toJSONString(restJson);
    }

    @PostMapping(value = {"/adminLogin"})
    public String adminLogin(@RequestBody User user){
        Boolean result = userService.loginAuthentication(user);
        Subject currentUser = SecurityUtils.getSubject();
        RestJson restJson = new RestJson();
        if(result){
            user.setUserType(Constants.USER_TYPE_ADMIN);
            user.setPassword(null);
            logger.debug("currentSessionID: "+currentUser.getSession().getId());
            user.setToken(EncryptionUtil.EncryptAES(String.valueOf(currentUser.getSession().getId())));
            restJson.setStatus(1);
            restJson.setData(user);
            return JSONObject.toJSONString(restJson);
        }
        restJson.setStatus(2);
        restJson.setDesc("Authentication error, password may not match");
        return  JSONObject.toJSONString(restJson);
    }


    @GetMapping(value = "/admin/getHostDetailById")
    public String getHostDetailById(@RequestParam Integer hostId){
        logger.debug("request host id: ---- "+hostId);
        RestJson restJson = new RestJson();
        HostDetail hostDetail = hostService.getHostDetailById(hostId);
        restJson.setData(hostDetail);
        return JSONObject.toJSONString(restJson);
    }

    @GetMapping(value = "/admin/propertyPictures")
    public String getPropertyPictureList(@RequestParam Integer propertyId){
        RestJson restJson = new RestJson();
        List<PropertyPictureBase64> pictureList = hostService.selectBase64PictureListByPropertyId(propertyId);
        restJson.setData(pictureList);
        return  JSONObject.toJSONString(restJson);
    }

    @PostMapping(value = "/admin/deleteHostDetail")
    public String deleteHostDetail(@RequestBody JSONObject jsonParam){
        Integer hostId = jsonParam.getInteger("hostId");
        logger.debug("Deleting host with id: ----"+hostId);
        boolean result = adminService.deleteHostByAdmin(hostId);
        RestJson restJson = new RestJson();
        if(!result){
            restJson.setStatus(2);
            restJson.setDesc("Failed to delete Host by Admin");
        }
        return JSONObject.toJSONString(restJson);
    }

    @PostMapping(value = "/admin/deleteRenter")
    public String deleteRenter(@RequestBody JSONObject jsonParam){
        Integer renterId = jsonParam.getInteger("renterId");
        logger.debug("Deleting renter with id: ----"+renterId);
        boolean result = adminService.deleteRenterByAdmin(renterId);
        RestJson restJson = new RestJson();
        if(!result){
            restJson.setStatus(2);
            restJson.setDesc("Failed to delete Renter by Admin");
        }
        return JSONObject.toJSONString(restJson);
    }

    @GetMapping(value = "/admin/getRenterDetailById")
    public String getRenterDetailById(@RequestParam Integer id){
        logger.debug("request id: ---- "+id);
        RestJson restJson = new RestJson();
        Renter renter = renterService.selectRenterById(id);
        restJson.setData(renter);
        return JSONObject.toJSONString(restJson);
    }

    @PostMapping(value="/admin/updateHostDetail")
    public String updateHostDetail(@RequestBody HostDetail hostDetail){
        logger.debug("request host: ----"+ JSONObject.toJSONString(hostDetail) );
//        logger.debug("propertyList:" + propertyList);
        RestJson restJson = new RestJson();
        Host host = hostDetail.getHost();
        host.setEmail(null);//no one can update user's email except user himself
        host.setPassword(null);//no one can update user's password except user himself;
        if (hostService.updateHost(host)){
            restJson.setDesc("successfully updated host");
        }else {
            restJson.setStatus(2);
            restJson.setDesc("Failed to save host detail, check log for more information");
        }
        List<Property> properties = hostDetail.getPropertyList();
        hostService.updateProperties(properties);
        return JSONObject.toJSONString(restJson);
    }

    @PostMapping(value="/admin/updateRenter")
    public String updateRenter(@RequestBody Renter renter){
        logger.debug("request renter: ----"+ JSONObject.toJSONString(renter) );
        RestJson restJson = new RestJson();
        renter.setEmail(null);//no one can update user's email except user himself
        renter.setPassword(null);//no one can update user's password except user himself;
        if (renterService.updateRenter(renter)){
            restJson.setDesc("successfully updated renter");
        }else {
            restJson.setStatus(2);
            restJson.setDesc("Failed to save host detail, check log for more information");
        }
        return JSONObject.toJSONString(restJson);
    }

    @GetMapping(value = "/admin/referralSourceAggregation")
    public String getReferralSourceAggregation(){
        RestJson restJson = new RestJson();
        Map<String, Long> result = adminService.getReferralSourceAggregation();
        if(result==null||result.isEmpty()){
            restJson.setStatus(2);
            restJson.setDesc("Failed to query referral source aggregated data");
        }
        restJson.setData(result);
        return JSONObject.toJSONString(restJson);
    }

    @GetMapping(value = "/admin/hostLoginRecordGroupByMonth")
    public String hostLoginRecordGroupByMonth(){
        Map<String, Long> result = adminService.selectLoginHostRecordGroupByMonth();
        RestJson restJson = new RestJson();
        if(result==null||result.isEmpty()){
            restJson.setStatus(2);
            restJson.setDesc("Failed to query host referral login aggregated records");
        }
        restJson.setData(result);
        return JSONObject.toJSONString(restJson);
    }

    @GetMapping(value = "/admin/renterLoginRecordGroupByMonth")
    public String renterLoginRecordGroupByMonth(){
        Map<String, Long> result = adminService.selectLoginRenterRecordGroupByMonth();
        RestJson restJson = new RestJson();
        if(result==null||result.isEmpty()){
            restJson.setStatus(2);
            restJson.setDesc("Failed to query renter referral login aggregated records");
        }
        restJson.setData(result);
        return JSONObject.toJSONString(restJson);
    }

    @GetMapping(value = "/admin/hostAggregatedData")
    public String hostAggregatedData(){
        Map<String, Long> result = adminService.selectHostAggregatedData();
        RestJson restJson = new RestJson();
        if(result==null||result.isEmpty()){
            restJson.setStatus(2);
            restJson.setDesc("Failed to query host aggregated data");
        }
        restJson.setData(result);
        return JSONObject.toJSONString(restJson);
    }

    @GetMapping(value = "/admin/renterAggregatedData")
    public String renterAggregatedData(){
        Map<String, Long> result = adminService.selectRenterAggregatedData();
        RestJson restJson = new RestJson();
        if(result==null||result.isEmpty()){
            restJson.setStatus(2);
            restJson.setDesc("Failed to query renter aggregated data");
        }
        restJson.setData(result);
        return JSONObject.toJSONString(restJson);
    }
}
