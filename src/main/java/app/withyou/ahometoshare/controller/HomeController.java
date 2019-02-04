package app.withyou.ahometoshare.controller;


import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    HostService hostService;

    @GetMapping("/")
    public String home(Model model){
        List<Host> hosts = hostService.getAllHosts();
        model.addAttribute("hosts",hosts);
        return "homepage";
    }
}
