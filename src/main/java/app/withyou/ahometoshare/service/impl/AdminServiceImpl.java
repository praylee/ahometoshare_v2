package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.AdminMapper;
import app.withyou.ahometoshare.dao.HostMapper;
import app.withyou.ahometoshare.dao.PropertyMapper;
import app.withyou.ahometoshare.model.Admin;
import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.HostDetail;
import app.withyou.ahometoshare.model.Property;
import app.withyou.ahometoshare.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private HostMapper hostMapper;

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public Admin selectAdminByUsername(String username) {
        return adminMapper.selectByUsername(username);
    }

    @Override
    public HostDetail getHostDetailByEmail(String email) {
        HostDetail hostDetail = new HostDetail();
        Host host = hostMapper.selectByEmail(email);
        host.setPassword("");
        List<Property> propertyList = propertyMapper.getPropertyListByHostId(host.getHostId());
        hostDetail.setHost(host);
        hostDetail.setPropertyList(propertyList);
        return hostDetail;
    }

}
