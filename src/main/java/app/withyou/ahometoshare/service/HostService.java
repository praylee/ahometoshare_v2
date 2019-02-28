package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Host;

import java.util.List;

public interface HostService {

    public List<Host> getAllHosts();

    public void saveHost(Host host);

    public Host selectHostByEmail(String email);

}
