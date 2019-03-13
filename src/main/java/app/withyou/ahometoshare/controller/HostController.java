package app.withyou.ahometoshare.controller;

import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class HostController implements WebMvcConfigurer {

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
    public String hostProfile(@ModelAttribute("msg") String message , Model model ){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Host host = hostService.selectHostByHostId(user.getUserPrimaryKey());
        model.addAttribute("host", host);
        model.addAttribute("msg", message);
        model.addAttribute("fullName",host.getFirstName()+" "+host.getLastName());
        return "hostProfile";
    }

    @PostMapping("/host/updateHost")
    public String updateHost(@ModelAttribute Host host, RedirectAttributes redirectAttributes){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Host h = hostService.selectHostByHostId(user.getUserPrimaryKey());
        host.setHostId(h.getHostId());
        if(hostService.updateHost(host)){
            redirectAttributes.addFlashAttribute("msg","Updated successfully");
        }
        else{
            redirectAttributes.addFlashAttribute("msg", "Failed to update");
        }
        return "redirect:/host/hostProfile";
    }

    @GetMapping("/host/hostAccountSettings")
    public String hostAccountSetting(@ModelAttribute("msg") String message, @ModelAttribute("form")UpdateAccountSettingForm form, Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Host host = hostService.selectHostByHostId(user.getUserPrimaryKey());
        model.addAttribute("fullName",host.getFirstName()+" "+host.getLastName());
        model.addAttribute("msg",message);
        form.setEmail(host.getEmail());
        return "hostAccountSettings";
    }


    @PostMapping("/host/hostAccountSettings")
    public String updateHostAccountSetting(@Valid @ModelAttribute("form")UpdateAccountSettingForm form, BindingResult bindingResult, Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Host host = hostService.selectHostByHostId(user.getUserPrimaryKey());
        model.addAttribute("fullName",host.getFirstName()+" "+host.getLastName());
        if (bindingResult.hasErrors()) {
            return "hostAccountSettings";
        }
        if (!form.getNewPassword().equals(form.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword","error.host",null,"confirm password do not match" );
        }
        if(!hostService.validatePassword(form.getOldPassword())){
            bindingResult.rejectValue("oldPassword", "error.host", null, "incorrect old password");
        }
        if (bindingResult.hasErrors()){
            return "hostAccountSettings";
        }
        if (!hostService.updateHostAccountSettings(form)){
            model.addAttribute("updateError","something wrong during update account setting, try later");
        }else{
            model.addAttribute("msg","update successfully");
        }

        return "hostAccountSettings";
    }


    @PostMapping("/host/deleteHost")
    public String deleteAccount(@RequestParam("old_pwd")String password, RedirectAttributes redirectAttributes){
        if(hostService.deleteAccount(password)){
            SecurityUtils.getSubject().logout();
            return "redirect:/homepage";
        }
        redirectAttributes.addFlashAttribute("deletionError","Fail to delete account, password may not match");
        return "redirect:/host/hostAccountSettings";
    }
}
