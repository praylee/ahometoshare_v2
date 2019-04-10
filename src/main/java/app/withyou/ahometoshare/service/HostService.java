/*
 * File: HostService.java
 * Author: Nan Jiang
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.*;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;
import javafx.util.Pair;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface HostService {

    public List<Host> getAllHosts();

    public boolean registerHost(Host host);

    public Host selectHostByEmail(String email);

    public boolean updateHost(Host host);

    public boolean updateProperties(List<Property> list);

    public HostDetail getHostDetailById(Integer id);

    public Host selectHostByHostId(Integer hostId);

    public boolean validatePassword(String password);

    public boolean updateHostAccountSettings(UpdateAccountSettingForm form);

    public boolean deleteAccount(String password);

    public List<Property> getPropertyListByHostId(Integer hostId);

    public boolean deletePropertyByPropertyId(Integer propertyId);

    public Pair<Boolean, String> insertProperty(Property property, HttpServletRequest request);

    public List<PropertyPicture> getPropertyImageByPropertyId(Integer propertyId);

    public List<PropertyPictureBase64> selectBase64PictureListByPropertyId(Integer propertyId);

}
