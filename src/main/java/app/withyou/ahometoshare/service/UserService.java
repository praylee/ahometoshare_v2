/*
 * File: UserService.java
 * Author: Peng Li
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.User;
import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;

public interface UserService {

    public Boolean loginAuthentication(User user);

    public User getUser(String username, String password);

    public User findUserByEmail(String email);

    public Pair<Boolean,String> resetPasswordForUser(String email, String firstName, String lastName);

}
