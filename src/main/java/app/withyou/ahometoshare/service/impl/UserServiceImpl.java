package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.AdminMapper;
import app.withyou.ahometoshare.dao.HostMapper;
import app.withyou.ahometoshare.dao.LoginRecordMapper;
import app.withyou.ahometoshare.dao.RenterMapper;
import app.withyou.ahometoshare.model.*;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import app.withyou.ahometoshare.utils.EmailUtil;
import app.withyou.ahometoshare.utils.MD5Util;
import javafx.util.Pair;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    LoginRecordMapper loginRecordMapper;
    @Autowired
    HostMapper hostMapper;
    @Autowired
    RenterMapper renterMapper;
    @Autowired
    AdminMapper adminMapper;


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Boolean loginAuthentication(User user){
        String username = user.getUsername();
        String password = user.getPassword();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            token.setRememberMe(user.getRememberMe());
            currentUser.login(token);
            logUserLogin();
            return true;
        }catch (AuthenticationException e){
            return false;
        }
    }

    private void logUserLogin(){
        User user = (User)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        LoginRecord record = new LoginRecord();
        record.setUsername(user.getUsername());
        record.setUsertype(user.getUserType());
        Date date = new Date();
        record.setTime(date);
        loginRecordMapper.insert(record);
    }

    public User getUser(String email, String password) {
        //TODO
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        Host host = hostMapper.selectByEmail(email);
        if(host != null){
            user.setUserPrimaryKey(host.getHostId());
            user.setUsername(host.getEmail());
            user.setPassword(host.getPassword());
            user.setFirstName(host.getFirstName());
            user.setLastName(host.getLastName());
            user.setUserType(Constants.USER_TYPE_HOST);
            return user;
        }
        Renter renter = renterMapper.selectByEmail(email);
        if(renter != null){
            user.setUserPrimaryKey(renter.getId());
            user.setUsername(renter.getEmail());
            user.setPassword(renter.getPassword());
            user.setFirstName(renter.getFirstName());
            user.setLastName(renter.getLastName());
            user.setUserType(Constants.USER_TYPE_RENTER);
            return user;
        }
        Admin admin = adminMapper.selectByUsername(email);
        if(admin != null){
            user.setUserPrimaryKey(admin.getId());
            user.setUsername(admin.getUsername());
            user.setPassword(admin.getPassword());
            user.setUserType(Constants.USER_TYPE_ADMIN);
            return user;
        }

        return null;
    }

    @Override
    public Pair<Boolean, String> resetPasswordForUser(String email, String firstName, String lastName) {
        User user = findUserByEmail(email);
        if(user==null||user.getUserType()==Constants.USER_TYPE_ADMIN){
            return new Pair<>(Boolean.FALSE, "This Email is not registered");
        }else if(firstName==null||lastName==null||!(firstName.trim().equalsIgnoreCase(user.getFirstName())&&lastName.trim().equalsIgnoreCase(user.getLastName()))){
            return new Pair<>(Boolean.FALSE, "Name does not match this Email");
        }
        try {
            String randomPassword = RandomStringUtils.randomAlphanumeric(8,10);
            if(user.getUserType()==Constants.USER_TYPE_HOST){
                Host host = hostMapper.selectByPrimaryKey(user.getUserPrimaryKey());
                String encryptedPassword = MD5Util.encryptWithMD5(randomPassword, host.getEmail());
                host.setPassword(encryptedPassword);
                hostMapper.updateByPrimaryKeySelective(host);
            }
            if(user.getUserType()==Constants.USER_TYPE_RENTER){
                Renter renter = renterMapper.selectByPrimaryKey(user.getUserPrimaryKey());
                String encryptedPassword = MD5Util.encryptWithMD5(randomPassword, renter.getEmail());
                renter.setPassword(encryptedPassword);
                renterMapper.updateByPrimaryKeySelective(renter);
            }
            EmailUtil.getInstance().sendResetPasswordEmail(email, randomPassword);
            return new Pair<>(Boolean.TRUE, "Successfully reset password, check email box");
        }catch (Exception e){
            logger.error("Failed to reset password for user", e);
            return new Pair<>(Boolean.FALSE, "Failed to reset password, try later");
        }
    }


    public static void main(String[] args){
        String randomPassword = RandomStringUtils.randomAlphanumeric(8,10);
        System.out.println(randomPassword);
    }

}
