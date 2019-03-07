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
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView renterRegisterSubmit(@ModelAttribute Renter renter) {
        int i =  renterService.saveRenter(renter);
        if(i==0){
            ModelAndView mv =  new ModelAndView();
            mv.addObject("renter", renter);
            mv.addObject("msg","Email has been taken");
            return mv;
        }
        return new ModelAndView("registerConfirm");
    }

    @GetMapping("/renter/renterProfile")
    public String renterRegisterSubmit(Model model) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterByEmail(user.getEmail());
        model.addAttribute("renter", renter);
        return "renterProfile";
    }
}
