/*
 * File: AjaxRestController.java
 * Author: Peng Li
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

package app.withyou.ahometoshare.controller;

import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.RenterService;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import app.withyou.ahometoshare.utils.RestJson;
import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AjaxRestController {

    @Autowired
    UserService userService;

    @Autowired
    HostService hostService;

    @Autowired
    RenterService renterService;

    @PostMapping(value = {"/login"})
    @ResponseBody
    public String login(@RequestBody User user){
        Boolean result = userService.loginAuthentication(user);
        RestJson restJson = new RestJson();
        if(result){
            int userType =(int) SecurityUtils.getSubject().getSession().getAttribute(Constants.USER_TYPE_STRING);
            user.setUserType(userType);
            user.setPassword("");
            restJson.setStatus(1);
            restJson.setData(user);
            return JSONObject.toJSONString(restJson);
        }
        restJson.setStatus(2);
        restJson.setDesc("Authentication error, password may not match");
        return  JSONObject.toJSONString(restJson);
    }

    @PostMapping("/forgotPassword")
    @ResponseBody
    public String resetPassword(@RequestBody JSONObject requestJson){
        String email = requestJson.getString("email");
        String firstName = requestJson.getString("firstName");
        String lastName = requestJson.getString("lastName");
        Pair<Boolean,String> result = userService.resetPasswordForUser(email, firstName, lastName);
        RestJson restJson = new RestJson();
        if(!result.getKey()){
            restJson.setStatus(2);
        }
        restJson.setDesc(result.getValue());
        return JSONObject.toJSONString(restJson);
    }


}
