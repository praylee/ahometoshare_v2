/*
 * File: PermissionServiceImpl.java
 * Author: Oroi Wang
 * Modified By: Peng Li
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Override
    public Set<String> getPermissionsByUser(User user) {
        Set<String> permissions = new HashSet<>();
        permissions.add("user:delete");
        permissions.add("user:update");
        return permissions;
    }
}
