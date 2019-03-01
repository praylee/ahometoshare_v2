package app.withyou.ahometoshare.controller;


import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.RenterService;
import app.withyou.ahometoshare.service.UserService;
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
        model.addAttribute("error","");
        return "homepage";
    }

    @GetMapping("/homepage")
    public String homepage(Model model){
        return home(model);
    }


}
