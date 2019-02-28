package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.User;

import java.util.Set;

public interface PermissionService {

    public Set<String> getPermissionsByUser(User user);
}
