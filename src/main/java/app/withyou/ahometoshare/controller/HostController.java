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

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/hostProfile")
    public ModelAndView hostProfile(ModelAndView mv){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        Host host = hostService.selectHostByEmail(user.getEmail());
        mv.addObject("host", host);
        return mv;
    }
}
