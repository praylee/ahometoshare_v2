/*
 * File: RenterController.java
 * Author: Milos Boskovic
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare.controller;

import app.withyou.ahometoshare.model.*;
import app.withyou.ahometoshare.model.form.FilterPropertyForm;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import app.withyou.ahometoshare.service.RenterService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class RenterController {

    @Autowired
    RenterService renterService;

    @Autowired
    UserService userService;

    @Autowired
    HostService hostService;
    
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
            mv.addObject("msg","Email already registered");
            return mv;
        }
        boolean result =  renterService.registerRenter(renter);
        if(!result){
            mv.addObject("msg","Something wrong with Renter registration, please try later");
            return mv;
        }
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


    @GetMapping("/renter/renterSearchProperty")
    public String renterSearchProperty(Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterById(user.getUserPrimaryKey());
        model.addAttribute("fullName",renter.getFirstName()+" "+renter.getLastName());
        model.addAttribute("cityList", Constants.CITY_lIST);
        model.addAttribute("filterForm",new FilterPropertyForm());
        return "renterSearchProperty";
    }

    @PostMapping("/renter/renterSearchProperty")
    public String renterSearchProperty(@ModelAttribute FilterPropertyForm filterForm, Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterById(user.getUserPrimaryKey());
        List<Property> properties = renterService.searchHostPropertiesByConditions(filterForm) ;
        model.addAttribute("fullName",renter.getFirstName()+" "+renter.getLastName());
        model.addAttribute("cityList", Constants.CITY_lIST);
        model.addAttribute("properties",properties);
        model.addAttribute("filterForm",filterForm);
        return "renterSearchProperty";
    }

    @PostMapping("/renter/renterSearchPropertyDetail")
    public String renterSearchPropertyDetail(@Valid Property p, Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterById(user.getUserPrimaryKey());
        Property property = renterService.selectPropertyByPropertyId(p.getPropertyId());
        Host host = hostService.selectHostByHostId(property.getHostId());
        List<PropertyPicture> list = renterService.getPropertyImageByPropertyId(property.getPropertyId());
        model.addAttribute("property", property);
        model.addAttribute("host", host);
        model.addAttribute("pictures", list);
        model.addAttribute("fullName",renter.getFirstName()+" "+renter.getLastName());
        return "renterSearchPropertyDetail";
    }

    @RequestMapping("/renter/getPropertyImage")
    @ResponseBody
    public String getPropertyImagePreview(@RequestParam Integer propertyId, HttpServletRequest request, HttpServletResponse response){
        List<PropertyPicture> list = renterService.getPropertyImageByPropertyId(propertyId);
        OutputStream os = null;
        if(!list.isEmpty()){
            try{
                os = response.getOutputStream();
                os.write(list.get(0).getPicture());
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }

    @RequestMapping("/renter/getPropertyImageById")
    @ResponseBody
    public String getAllPropertyImage(@RequestParam Integer pictureId, HttpServletRequest request, HttpServletResponse response){
        PropertyPicture propertyPicture = renterService.selectPropertyPictureByPictureId(pictureId);
        OutputStream os = null;
        try{
            os = response.getOutputStream();
            os.write(propertyPicture.getPicture());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return "ok";
    }

    @PostMapping("/renter/bookPropertyRequest")
    public String bookPropertyRequest(@RequestParam Integer propertyId, Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Renter renter = renterService.selectRenterById(user.getUserPrimaryKey());
        Property property = renterService.selectPropertyByPropertyId(propertyId);
        Host host = hostService.selectHostByHostId(property.getHostId());
        Boolean result = renterService.bookPropertyRequest(renter, property);
        model.addAttribute("renter", renter);
        model.addAttribute("host", host);
        model.addAttribute("fullName",renter.getFirstName()+" "+renter.getLastName());
        model.addAttribute("result", result);
        return "bookPropertyRequest";
    }

}
