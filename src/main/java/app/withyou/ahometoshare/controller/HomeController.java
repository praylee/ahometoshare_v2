/*
 * File: HomeController.java
 * Author: Peng Li
 * Modified By: Milos Boskovic
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
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    HostService hostService;

    @Autowired
    RenterService renterService;



    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("user", new User());
        return "homepage";
    }

    @GetMapping("/homepage")
    public String homepage(Model model){
        return home(model);
    }

    @GetMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/homepage";
    }

    @GetMapping("/myProfile")
    public String myProfile(){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        if (user.getUserType() == Constants.USER_TYPE_HOST){
            return "redirect:/host/hostProfile";
        }
        if(user.getUserType() == Constants.USER_TYPE_RENTER){
            return  "redirect:/renter/renterProfile";
        }
        return "redirect:/homepage";
    }

}
