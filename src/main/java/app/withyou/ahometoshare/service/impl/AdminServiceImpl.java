package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.AdminMapper;
import app.withyou.ahometoshare.model.Admin;
import app.withyou.ahometoshare.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin selectAdminByUsername(String username) {
        return adminMapper.selectByUsername(username);
    }
}
