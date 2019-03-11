package app.withyou.ahometoshare.controller;


import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.HostDetail;
import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.AdminService;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.RenterService;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import app.withyou.ahometoshare.utils.EncryptionUtil;
import app.withyou.ahometoshare.utils.RestJson;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
            user.setPassword("");
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


    @GetMapping(value = "/admin/getHostDetailByEmail")
    public String getHostDetailByEmail(@RequestParam String email){
        logger.debug("request email: ---- "+email);
        RestJson restJson = new RestJson();
        HostDetail hostDetail = adminService.getHostDetailByEmail(email);
        restJson.setData(hostDetail);
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

    @PostMapping(value="/admin/saveHostDetail")
    public String saveHostDetail(@RequestBody HostDetail hostDetail){
        logger.debug("request host: ----"+ JSONObject.toJSONString(hostDetail) );
        RestJson restJson = new RestJson();
        if (hostService.updateHost(hostDetail.getHost())){
            restJson.setDesc("successfully saved host");
        }else {
            restJson.setStatus(2);
            restJson.setDesc("Failed to save host detail, check log for more information");
        }
//        hostService.updateProperties();
        return JSONObject.toJSONString(restJson);
    }

}
