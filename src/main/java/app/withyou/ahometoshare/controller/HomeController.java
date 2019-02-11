package app.withyou.ahometoshare.controller;


import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;
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

    @Autowired
    HostService hostService;

    @GetMapping("/")
    public String home(Model model){
        return "homepage";
    }

    @GetMapping("/homepage")
    public String homepage(Model model){
        return home(model);
    }

    @GetMapping("/hostRegister")
    public String hostRegister(Model model){
        model.addAttribute("host", new Host());
        return "hostRegister";
    }


    @PostMapping("hostRegister")
    public String hostRegisterSubmit(@ModelAttribute Host host){
        hostService.saveHost(host);
        return "registerConfirm";
    }

}
