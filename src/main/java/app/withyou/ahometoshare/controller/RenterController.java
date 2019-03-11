package app.withyou.ahometoshare.controller;

import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.UserService;
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

    @Autowired
    UserService userService;
    
    @GetMapping("/renterRegister")
    public String renterRegister(Model model) {
        model.addAttribute("renter", new Renter());
        return "renterRegister";
    }
    
    @PostMapping("/renterRegister")
    public ModelAndView renterRegisterSubmit(@ModelAttribute Renter renter) {
        User user = userService.findUserByEmail(renter.getEmail());
        ModelAndView mv =  new ModelAndView("renterRegister");
        mv.addObject("renter", renter);
        if(user!=null){
            mv.addObject("msg","Email has been taken");
            return mv;
        }
        int i =  renterService.insertRenter(renter);
        if(i==0){
            mv.addObject("msg","Something wrong with Renter registration, please try later");
            return mv;
        }
        return new ModelAndView("registerConfirm");
    }

    @GetMapping("/renter/renterProfile")
    public String renterRegisterSubmit(Model model) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterByEmail(user.getUsername());
        model.addAttribute("renter", renter);
        return "renterProfile";
    }
}
