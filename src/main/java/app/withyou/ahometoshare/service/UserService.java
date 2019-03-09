package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.User;
import com.alibaba.fastjson.JSONObject;

public interface UserService {

    public Boolean loginAuthentication(User user);

    public User getUser(String username, String password);

    public User findUserByEmail(String email);

    public JSONObject getInfo();
}
