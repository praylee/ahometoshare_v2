package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Admin;

public interface AdminService {

    public Admin selectAdminByUsername(String username);
}
