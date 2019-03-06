package app.withyou.ahometoshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPageController {

    @GetMapping("/static/howWeWork")
    public String howWeWork(Model model) {
        return "howWeWork";
    }
    
    @GetMapping("/static/aboutUs")
    public String aboutUs(Model model) {
        return "aboutUs";
    }
    
    
}
