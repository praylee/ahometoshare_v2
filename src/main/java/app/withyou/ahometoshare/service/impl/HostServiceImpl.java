package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.HostMapper;
import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    @Autowired
    private HostMapper hostMapper;

    @Override
    public List<Host> getAllHosts() {
        return hostMapper.selectAll();
    }

    @Override
    public void saveHost(Host host) {
        Md5Hash password = new Md5Hash( host.getPassword(), host.getEmail());
        host.setPassword(password.toString());
        hostMapper.insert(host);
    }

    @Override
    public Host selectHostByEmail(String email) {
        return hostMapper.selectByEmail(email);
    }
}
