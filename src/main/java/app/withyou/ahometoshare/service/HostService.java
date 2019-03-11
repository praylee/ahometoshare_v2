package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.HostDetail;
import app.withyou.ahometoshare.model.Property;

import java.util.List;

public interface HostService {

    public List<Host> getAllHosts();

    public int insertHost(Host host);

    public Host selectHostByEmail(String email);

    public boolean updateHost(Host host);

    public boolean updateProperties(List<Property> list);

    public HostDetail getHostDetailById(Integer id);

}
