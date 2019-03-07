package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.HostMapper;
import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    @Autowired
    private HostMapper hostMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<Host> getAllHosts() {
        List<Host> hosts = hostMapper.selectAll();
        hosts.stream().forEach(host -> host.setPassword(""));
        return hosts;
    }

    @Override
    public int saveHost(Host host) {
        User user = userService.findUserByEmail(host.getEmail());
        if(user!=null){
            return 0;
        }
        Md5Hash password = new Md5Hash( host.getPassword(), host.getEmail());
        host.setPassword(password.toString());
        return hostMapper.insert(host);
    }

    @Override
    public Host selectHostByEmail(String email) {
        return hostMapper.selectByEmail(email);
    }
}
