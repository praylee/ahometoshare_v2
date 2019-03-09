package app.withyou.ahometoshare.controller;


import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.HostService;
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


@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class AdminRestController {

    private static Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    HostService hostService;

    @Autowired
    UserService userService;



    @RequestMapping(value = "/admin/getAllHosts" ,method = RequestMethod.GET)
    public String getAllHosts(){
        RestJson restJson = new RestJson();
        List<Host> hosts= hostService.getAllHosts();
        restJson.setData(hosts);
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

    @PostMapping(value = {"/admin/getAdminInfo"})
    public String getAdminInfo(@RequestBody User user){
        RestJson restJson = new RestJson();
        restJson.setDesc("get admin info success");
        return JSONObject.toJSONString(restJson);
    }


}
