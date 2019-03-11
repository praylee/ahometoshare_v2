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

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HostController {

    @Autowired
    HostService hostService;

    @Autowired
    UserService userService;
    
    @GetMapping("/hostRegister")
    public String hostRegister(Model model){
        model.addAttribute("host", new Host());
        return "hostRegister";
    }


    @PostMapping("/hostRegister")
    public ModelAndView hostRegisterSubmit(@ModelAttribute Host host){
        User user = userService.findUserByEmail(host.getEmail());
        ModelAndView mv =  new ModelAndView("hostRegister");
        mv.addObject("host", host);
        if(user!=null){
            mv.addObject("msg","Email has been taken");
            return mv;
        }
        int i = hostService.insertHost(host);
        if (i==0){
            mv.addObject("msg","Something wrong with Host registration, please try later");
            return mv;
        }
        return new ModelAndView("registerConfirm");
    }

    @GetMapping("/host/hostProfile")
    public String hostProfile(Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Host host = hostService.selectHostByEmail(user.getUsername());
        model.addAttribute("host", host);
        return "hostProfile";
    }
}
