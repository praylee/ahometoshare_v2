package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Admin;
import app.withyou.ahometoshare.model.HostDetail;

public interface AdminService {

    public Admin selectAdminByUsername(String username);

}
