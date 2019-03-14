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

import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.service.RenterService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import app.withyou.ahometoshare.utils.*;

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
        EmailUtil.sendEmail(renter.getEmail());
        return new ModelAndView("registerConfirm");
    }

    @GetMapping("/renter/renterProfile")
    public String getRenterProfile(Model model ) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterById(user.getUserPrimaryKey());
        model.addAttribute("renter", renter);
        model.addAttribute("fullName",renter.getFirstName()+" "+renter.getLastName());
        return "renterProfile";
    }

    @PostMapping("/renter/renterProfile")
    public String updateRenterProfile(@ModelAttribute Renter renter, Model model){
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter r = renterService.selectRenterById(user.getUserPrimaryKey());
        renter.setId(r.getId());

        if(renterService.updateRenter(renter)){
            model.addAttribute("msg","Updated successfully");
        }else {
            model.addAttribute("msg", "Failed to update");
        }
        model.addAttribute("fullName",renter.getFirstName()+" "+renter.getLastName());
        return "renterProfile";
    }

    @GetMapping("/renter/renterAccountSettings")
    public String renterAccountSetting(@ModelAttribute("msg") String message, @ModelAttribute("form") UpdateAccountSettingForm form, Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterById(user.getUserPrimaryKey());
        model.addAttribute("fullName",renter.getFirstName()+" "+renter.getLastName());
        model.addAttribute("msg",message);
        form.setEmail(renter.getEmail());
        return "renterAccountSettings";
    }


    @PostMapping("/renter/renterAccountSettings")
    public String updateRenterAccountSetting(@Valid @ModelAttribute("form")UpdateAccountSettingForm form, BindingResult bindingResult, Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterById(user.getUserPrimaryKey());
        model.addAttribute("fullName",renter.getFirstName()+" "+renter.getLastName());
        if (bindingResult.hasErrors()) {
            return "renterAccountSettings";
        }
        if (!form.getNewPassword().equals(form.getConfirmPassword())){
            bindingResult.rejectValue("confirmPassword","error.renter",null,"confirm password do not match" );
        }
        if(!renterService.validatePassword(form.getOldPassword())){
            bindingResult.rejectValue("oldPassword", "error.renter", null, "incorrect old password");
        }
        if (bindingResult.hasErrors()){
            return "renterAccountSettings";
        }
        if (!renterService.updateRenterAccountSettings(form)){
            model.addAttribute("updateError","something wrong during update account setting, try later");
        }else {
            model.addAttribute("msg","update successfully");
        }
        return "renterAccountSettings";
    }

    @PostMapping("/renter/deleteRenter")
    public String deleteAccount(@RequestParam("old_pwd")String password, RedirectAttributes redirectAttributes){
        if(renterService.deleteAccount(password)){
            SecurityUtils.getSubject().logout();
            return "redirect:/homepage";
        }
        redirectAttributes.addFlashAttribute("deletionError","Fail to delete account, password may not match");
        return "redirect:/renter/renterAccountSettings";
    }

}
