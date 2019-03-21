package app.withyou.ahometoshare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @GetMapping("/static/faq")
    public String faq(Model model) {
       return "FAQ"; 
    }

    
}
