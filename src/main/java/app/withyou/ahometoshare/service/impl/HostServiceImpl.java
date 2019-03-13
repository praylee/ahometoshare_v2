package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.HostMapper;
import app.withyou.ahometoshare.dao.PropertyMapper;
import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.HostDetail;
import app.withyou.ahometoshare.model.Property;
import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.utils.Constants;
import app.withyou.ahometoshare.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);

    @Autowired
    private HostMapper hostMapper;


    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<Host> getAllHosts() {
        List<Host> hosts = hostMapper.selectAll();
        hosts.stream().forEach(host -> host.setPassword(""));
        return hosts;
    }

    @Override
    public int insertHost(Host host) {
        String password = MD5Util.encryptWithMD5(host.getPassword(), host.getEmail());
        host.setPassword(password);
        return hostMapper.insert(host);
    }

    @Override
    public Host selectHostByEmail(String email) {
        return hostMapper.selectByEmail(email);
    }

    @Override
    public boolean updateHost(Host host) {
        try{
            if(hostMapper.updateByPrimaryKeySelective(host)==0) return false;
            else return true;
        }catch (RuntimeException e){
            logger.error("Fail to update host",e);
            return false;
        }
    }

    @Override
    public boolean updateProperties(List<Property> list){
        try {
            list.forEach(property -> propertyMapper.updateByPrimaryKeySelective(property));
        }catch (RuntimeException e){
            logger.error("Fail to update Property",e);
            return false;
        }
        return true;
    }

    @Override
    public HostDetail getHostDetailById(Integer id) {
        HostDetail hostDetail = new HostDetail();
        Host host = hostMapper.selectByPrimaryKey(id);
        host.setPassword("");
        List<Property> propertyList = propertyMapper.getPropertyListByHostId(host.getHostId());
        hostDetail.setHost(host);
        hostDetail.setPropertyList(propertyList);
        return hostDetail;
    }

    @Override
    public Host selectHostByHostId(Integer hostId) {
        Host host = hostMapper.selectByPrimaryKey(hostId);
        return host;
    }

    @Override
    public boolean validatePassword(String password) {
        User user =(User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
        String encryptedPassword = selectHostByHostId(user.getUserPrimaryKey()).getPassword();
        String hashedPwd = MD5Util.encryptWithMD5(password,user.getUsername());
        return encryptedPassword.equals(hashedPwd);
    }

    @Override
    public boolean updateHostAccountSettings(UpdateAccountSettingForm form) {
        try{
            User user =(User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
            Host host = selectHostByHostId(user.getUserPrimaryKey());
            host.setEmail(form.getEmail());
            host.setPassword( MD5Util.encryptWithMD5(form.getConfirmPassword(),form.getEmail()));
            updateHost(host);
            user.setUsername(host.getEmail());
            SecurityUtils.getSubject().getSession().removeAttribute(Constants.SESSION_USER);
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER, user);
            return true;
        }catch (Exception e){
            logger.error("Failed to update host account settings", e);
            return false;
        }
    }

    @Override
    public boolean deleteAccount(String password) {
        if (validatePassword(password)){
            User user =(User) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER);
            Host host = selectHostByHostId(user.getUserPrimaryKey());
            if (host ==null){
                return false;
            }
            int result = hostMapper.deleteByPrimaryKey(host.getHostId());
            if(result==1){
                return true;
            }
            return false;
        }
        return false;
    }


}
