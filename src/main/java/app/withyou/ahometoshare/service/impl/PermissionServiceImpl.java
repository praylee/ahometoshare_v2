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
