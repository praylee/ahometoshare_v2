package app.withyou.ahometoshare.controller;

import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.service.RenterService;

@Controller
public class RenterController {

    @Autowired
    RenterService renterService;
    
    @GetMapping("/renterRegister")
    public String renterRegister(Model model) {
        model.addAttribute("renter", new Renter());
        return "renterRegister";
    }
    
    @PostMapping("/renterRegister")
    public String renterRegisterSubmit(@ModelAttribute Renter renter) {
        renterService.saveRenter(renter);
        return "registerConfirm";
    }

    @GetMapping("/renter/renterProfile")
    public String renterRegisterSubmit(Model model) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterByEmail(user.getEmail());
        model.addAttribute("renter", renter);
        return "renterProfile";
    }
}
