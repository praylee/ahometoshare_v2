package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.AdminMapper;
import app.withyou.ahometoshare.dao.HostMapper;
import app.withyou.ahometoshare.dao.LoginRecordMapper;
import app.withyou.ahometoshare.dao.RenterMapper;
import app.withyou.ahometoshare.model.*;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
            user.setUserType(Constants.USER_TYPE_HOST);
            return user;
        }
        Renter renter = renterMapper.selectByEmail(email);
        if(renter != null){
            user.setUserPrimaryKey(renter.getId());
            user.setUsername(renter.getEmail());
            user.setPassword(renter.getPassword());
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


}
