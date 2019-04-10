/*
 * File: PermissionService.java
 * Author: Nan Jiang
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.User;

import java.util.Set;

public interface PermissionService {

    public Set<String> getPermissionsByUser(User user);
}
