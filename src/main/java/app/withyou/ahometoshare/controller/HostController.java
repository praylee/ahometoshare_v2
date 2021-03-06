/*
 * File: HostController.java
 * Author: Milos Boskovic
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

package app.withyou.ahometoshare.controller;

import app.withyou.ahometoshare.model.Property;
import app.withyou.ahometoshare.model.PropertyPicture;
import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import javafx.util.Pair;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
            mv.addObject("msg","Email already registered");
            return mv;
        }
        boolean result = hostService.registerHost(host);
        if (!result){
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

    @GetMapping("/host/propertyProfile")
    public String propertyProfile(Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Host host = hostService.selectHostByHostId(user.getUserPrimaryKey());
        List<Property> properties = hostService.getPropertyListByHostId(host.getHostId());
        model.addAttribute("properties", properties);
        model.addAttribute("host", host);
        model.addAttribute("fullName",host.getFirstName()+" "+host.getLastName());
        return "propertyProfile";
    }

    @PostMapping("/host/deleteHostProperty")
    public String deleteHostProperty(@RequestParam("propertyId")Integer propertyId){
        hostService.deletePropertyByPropertyId(propertyId);
        return "redirect:/host/propertyProfile";
    }

    @GetMapping("/host/roomPosting")
    public String roomPosting(Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Host host = hostService.selectHostByHostId(user.getUserPrimaryKey());
        model.addAttribute("fullName",host.getFirstName()+" "+host.getLastName());
        model.addAttribute("property",new Property());
        model.addAttribute("cityList", Constants.CITY_lIST);
        return "roomPosting";

    }


    @PostMapping("/host/roomPosting")
    public String roomPosting(@ModelAttribute Property property, HttpServletRequest request, Model model){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Host host = hostService.selectHostByHostId(user.getUserPrimaryKey());
        property.setHostId(host.getHostId());
        Pair<Boolean,String> result =  hostService.insertProperty(property, request);
        if(result.getKey()){
            return "redirect:/host/propertyProfile";
        }else{
            model.addAttribute("fullName",host.getFirstName()+" "+host.getLastName());
            model.addAttribute("property",property);
            model.addAttribute("cityList", Constants.CITY_lIST);
            model.addAttribute("errorMsg",result.getValue());
            return "roomPosting";
        }
    }

    @RequestMapping("/host/getPropertyImage")
    @ResponseBody
    public String getPropertyImage(@RequestParam Integer propertyId, HttpServletRequest request, HttpServletResponse response){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        Host host = hostService.selectHostByHostId(user.getUserPrimaryKey());
        List<PropertyPicture> list = hostService.getPropertyImageByPropertyId(propertyId);
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

}
