package app.withyou.ahometoshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;

@Controller
public class HostController {

    @Autowired
    HostService hostService;
    
    @GetMapping("/hostRegister")
    public String hostRegister(Model model){
        model.addAttribute("host", new Host());
        return "hostRegister";
    }


    @PostMapping("/hostRegister")
    public String hostRegisterSubmit(@ModelAttribute Host host){
        hostService.saveHost(host);
        return "registerConfirm";
    }
    
}
