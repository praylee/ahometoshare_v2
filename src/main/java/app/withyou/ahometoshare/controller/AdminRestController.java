package app.withyou.ahometoshare.controller;


import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import app.withyou.ahometoshare.utils.RestJson;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AdminRestController {
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
    public String adminLogin(User user){
        Boolean result = userService.loginAuthentication(user);
        RestJson restJson = new RestJson();
        if(result){
            user.setUserType(Constants.USER_TYPE_ADMIN);
            user.setPassword("");
            restJson.setStatus(1);
            restJson.setData(user);
            return JSONObject.toJSONString(restJson);
        }
        restJson.setStatus(2);
        restJson.setDesc("Authentication error, password may not match");
        return  JSONObject.toJSONString(restJson);
    }


}
