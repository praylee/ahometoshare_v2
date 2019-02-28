package app.withyou.ahometoshare.controller;


import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.RenterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {


    


    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("error","");
        return "homepage";
    }

    @GetMapping("/homepage")
    public String homepage(Model model){
        return home(model);
    }


}
