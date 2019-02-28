package app.withyou.ahometoshare.controller;

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.RenterService;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    HostService hostService;

    @Autowired
    RenterService renterService;

    @PostMapping("/login")
    public ModelAndView loginAuth(ModelAndView model, @ModelAttribute User user){
        Boolean result = userService.loginAuthentication(user);
        ModelAndView modelAndView;
        if(result){
            int usertype =(int)SecurityUtils.getSubject().getSession().getAttribute(Constants.USER_TYPE_STRING);
            if (usertype==1){
                Host host = hostService.selectHostByEmail(user.getEmail());
                modelAndView = new ModelAndView("redirect:/hostProfile");
                modelAndView.addObject("host",host);
                return modelAndView;
            }
            if (usertype ==2){
                Renter renter = renterService.selectRenterByEmail(user.getEmail());
                modelAndView = new ModelAndView("redirect:/renterProfile");
                modelAndView.addObject("renter",renter);
                return modelAndView;
            }
        }
        model.addObject("error","Authentication error, password may not match");
        return model;
    }

}
