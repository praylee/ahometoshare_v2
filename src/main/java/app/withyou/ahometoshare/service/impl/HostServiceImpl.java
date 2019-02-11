package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.HostMapper;
import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.service.HostService;
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
        hostMapper.insert(host);
    }
}
