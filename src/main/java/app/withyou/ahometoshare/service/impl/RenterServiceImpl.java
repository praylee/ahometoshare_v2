package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;
import app.withyou.ahometoshare.utils.Constants;
import app.withyou.ahometoshare.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.withyou.ahometoshare.dao.RenterMapper;
import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.service.RenterService;

import java.util.List;

@Service
public class RenterServiceImpl implements RenterService{

    Logger logger = LoggerFactory.getLogger(RenterService.class);

    @Autowired
    private RenterMapper renterMapper;

    @Override
    public int insertRenter(Renter renter) {
        String password = MD5Util.encryptWithMD5(renter.getPassword(), renter.getEmail());
        renter.setPassword(password);
        return renterMapper.insert(renter);
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


}
