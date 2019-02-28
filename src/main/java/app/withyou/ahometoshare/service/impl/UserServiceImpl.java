package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.service.RenterService;
import app.withyou.ahometoshare.utils.Constants;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    HostService hostService;

    @Autowired
    RenterService renterService;

    @Override
    public Boolean loginAuthentication(User user){
        String username = user.getEmail();
        String password = new String(user.getPassword());
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            currentUser.login(token);
            return true;
        }catch (AuthenticationException e){
            return false;
        }
    }

    public User getUser(String email, String password) {
        //TODO
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        Host host = hostService.selectHostByEmail(email);
        if(host != null){
            user.setEmail(host.getEmail());
            user.setPassword(host.getPassword());
            user.setUserType(Constants.USER_TYPE_HOST);
            return user;
        }
        Renter renter = renterService.selectRenterByEmail(email);
        if(renter != null){
            user.setEmail(renter.getEmail());
            user.setPassword(renter.getPassword());
            user.setUserType(Constants.USER_TYPE_RENTER);
            return user;
        }
        return null;
    }

    public JSONObject getInfo(){
        return null;
    }
}
