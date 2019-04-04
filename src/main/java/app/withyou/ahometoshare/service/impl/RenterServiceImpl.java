package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.*;
import app.withyou.ahometoshare.model.*;
import app.withyou.ahometoshare.model.form.FilterPropertyForm;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;
import app.withyou.ahometoshare.utils.Constants;
import app.withyou.ahometoshare.utils.EmailUtil;
import app.withyou.ahometoshare.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.withyou.ahometoshare.service.RenterService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RenterServiceImpl implements RenterService{

    Logger logger = LoggerFactory.getLogger(RenterService.class);

    @Autowired
    private RenterMapper renterMapper;


    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyPictureMapper propertyPictureMapper;

    @Autowired
    private HostMapper hostMapper;

    @Autowired
    private HomeRequestMapper homeRequestMapper;

    @Override
    public boolean registerRenter(Renter renter) {
        try{
            String password = MD5Util.encryptWithMD5(renter.getPassword(), renter.getEmail());
            renter.setPassword(password);
            int result = renterMapper.insert(renter);
            if (result==0) return false;
            EmailUtil.getInstance().sendRegistrationEmail(renter.getEmail(), renter.getFirstName()+" "+renter.getLastName());
            return true;
        }catch (Exception e){
            logger.error("Fail to register renter", e);
            return false;
        }
    }

    @Override
    public Renter selectRenterByEmail(String email) {
        return renterMapper.selectByEmail(email);
    }

    @Override
    public List<Renter> getAllRenters() {
        List<Renter> renters =  renterMapper.selectAll();
        renters.stream().forEach(renter -> renter.setPassword(""));
        return renters;
    }

    @Override
    public Renter selectRenterById(Integer id) {
        return renterMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateRenter(Renter renter) {
        try {
            renterMapper.updateByPrimaryKeySelective(renter);
        }catch (Exception e){
            logger.error("Fail to update Renter ",e);
            return false;
        }
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        User user =(User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        String encryptedPassword = selectRenterById(user.getUserPrimaryKey()).getPassword();
        String hashedPwd = MD5Util.encryptWithMD5(password,user.getUsername());
        return encryptedPassword.equals(hashedPwd);
    }

    @Override
    public boolean updateRenterAccountSettings(UpdateAccountSettingForm form) {
        try{
            User user =(User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
            Renter renter = selectRenterById(user.getUserPrimaryKey());
            renter.setEmail(form.getEmail());
            renter.setPassword( MD5Util.encryptWithMD5(form.getConfirmPassword(),form.getEmail()));
            updateRenter(renter);
            user.setUsername(renter.getEmail());
            SecurityUtils.getSubject().getSession().removeAttribute(Constants.SESSION_USER);
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER, user);
            return true;
        }catch (Exception e){
            logger.error("Failed to update host account settings", e);
            return false;
        }
    }

    @Override
    public boolean deleteAccount(String password) {
        if (validatePassword(password)){
            User user =(User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
            Renter renter = selectRenterById(user.getUserPrimaryKey());
            if (renter ==null){
                return false;
            }
            int result = renterMapper.deleteByPrimaryKey(renter.getId());
            if(result==1){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public List<Property> searchHostPropertiesByConditions(FilterPropertyForm form) {
       return propertyMapper.selectAll().stream()
       .filter(
           p -> {
               if("none".equalsIgnoreCase(form.getCity())){
                   return true;
               }else {
                   return  p.getCity().equalsIgnoreCase(form.getCity());
               }
           }
       ).filter(
           p ->{
           if("1".equalsIgnoreCase(form.getPrice())){
               return p.getPrice()<500;
           }else if("2".equalsIgnoreCase(form.getPrice())){
               return p.getPrice()>=500&&p.getPrice()<=800;
           }else if("3".equalsIgnoreCase(form.getPrice())){
               return p.getPrice()>800;
           }else {
               return true;
           }
       }).filter(p->{
          if(form.isInternet()) return p.getInternet()==true; else return true;
       }).filter(p->{
           if(form.isHydro())return p.getHydro()==true; else return true;
       }).filter(p->{
           if(form.isLaundry()) return p.getLaundry()==true; else return true;
       }).filter(p->{
           if(form.isPets())return p.getPets()==true; else return true;
       }).filter(p->{
           if(form.isParking()) return p.getParking()==true; else return true;
       }).filter(p->{
           if(form.isPrivateBedroom()) return p.getPrivateBedroom()==true; else return true;
       }).filter(p->{
           if(form.isSmoker()) return p.getSmoker()==true; else return true;
       }).collect(Collectors.toList());
    }

    @Override
    public Property selectPropertyByPropertyId(Integer propertyId) {
        return propertyMapper.selectByPrimaryKey(propertyId);
    }


    @Override
    public List<PropertyPicture> getPropertyImageByPropertyId(Integer propertyId) {
        List<PropertyPicture> list = propertyPictureMapper.selectByPropertyId(propertyId);
        return list;
    }

    @Override
    public PropertyPicture selectPropertyPictureByPictureId(Integer pictureId) {
        return propertyPictureMapper.selectByPrimaryKey(pictureId);
    }

    @Override
    public boolean bookPropertyRequest(Renter renter, Property property) {
        Host host = hostMapper.selectByPrimaryKey(property.getHostId());
        try{
            String bookRequestContent =
                    "Hello, " + "\n\n"
                    + renter.getFirstName() + " " + renter.getLastName() + " (" + renter.getEmail() + ") " + "would like to request a booking with " + host.getFirstName() + " " + host.getLastName() + " (" + host.getEmail() + ")"
                    + "at address: " + property.getAddress() + ", Postal Code: "+ property.getPostalCode() +"."
                    + "\n\n This is an automated message from generated by A Home to Share website.";
            EmailUtil.getInstance().sendBookingRequestEmail(bookRequestContent);
            HomeRequest homeRequest = new HomeRequest();
            homeRequest.setHost(host.getEmail());
            homeRequest.setHostName(host.getFirstName()+" "+host.getLastName());
            homeRequest.setRenter(renter.getEmail());
            homeRequest.setRenterName(renter.getFirstName()+" "+renter.getLastName());
            homeRequest.setPropertyAddress(property.getAddress()+", "+property.getCity());
            homeRequest.setRequestTime(new Date());
            homeRequestMapper.insert(homeRequest);
            return true;
        }catch (Exception e){
            logger.error("Failed to send booking request message", e);
            return false;
        }
    }


}
