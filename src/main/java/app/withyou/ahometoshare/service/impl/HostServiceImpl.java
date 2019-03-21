package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.dao.HostMapper;
import app.withyou.ahometoshare.dao.PropertyMapper;
import app.withyou.ahometoshare.dao.PropertyPictureMapper;
import app.withyou.ahometoshare.model.*;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;
import app.withyou.ahometoshare.service.HostService;
import app.withyou.ahometoshare.utils.Constants;
import app.withyou.ahometoshare.utils.EmailUtil;
import app.withyou.ahometoshare.utils.MD5Util;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);

    @Autowired
    private HostMapper hostMapper;


    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyPictureMapper propertyPictureMapper;

    @Override
    public List<Host> getAllHosts() {
        List<Host> hosts = hostMapper.selectAll();
        hosts.stream().forEach(host -> host.setPassword(""));
        return hosts;
    }

    @Override
    public boolean registerHost(Host host) {
        try{
            String password = MD5Util.encryptWithMD5(host.getPassword(), host.getEmail());
            host.setPassword(password);
            int result = hostMapper.insert(host);
            if(result == 0) return false;
            EmailUtil.getInstance().sendRegistrationEmail(host.getEmail(), host.getFirstName()+ " "+ host.getLastName());
            return true;
        }catch (Exception e){
            logger.error("Fail to register user", e);
            return false;
        }
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

    @Override
    public List<Property> getPropertyListByHostId(Integer hostId) {
        List<Property> propertyList = propertyMapper.getPropertyListByHostId(hostId);
        return propertyList;
    }


    @Override
    public boolean deletePropertyByPropertyId(Integer propertyId){
        try{
            propertyMapper.deleteByPrimaryKey(propertyId);
            return true;
        }catch (Exception e){
            logger.error("Failed to delete property with id: " + propertyId);
            return false;
        }
    }

    @Override
    public boolean insertProperty(Property property) {
        try{
            propertyMapper.insert(property);
            return true;
        }catch (Exception e){
            logger.error("Failed to property property");
            return false;
        }
    }

    @Override
    public boolean insertPropertyPicture(HttpServletRequest request, int propertyId) {
        String files[] = {"inputfile","inputfile2","inputfile3","inputfile4","inputfile5","inputfile6"};
        for(int f=0;f<files.length;f++){
            try {
                Part part = request.getPart(files[f]);
                if(part != null && part.getSize()> 0){
                    PropertyPicture propertypicture = new PropertyPicture();
                    propertypicture.setPropertyId(propertyId);
                    InputStream is = part.getInputStream();
                    propertypicture.setPicture(IOUtils.toByteArray(is));
                    propertyPictureMapper.insert(propertypicture);
                }
            } catch (IOException e) {
                logger.error("IOException",e);
                return false;
            } catch (ServletException e) {
                logger.error("ServletException",e);
                return false;
            }
        }
        return true;
    }

    @Override
    public List<PropertyPicture> getPropertyImageByPropertyId(Integer propertyId) {
        List<PropertyPicture> list = propertyPictureMapper.selectByPropertyId(propertyId);
        return list;
    }

}
