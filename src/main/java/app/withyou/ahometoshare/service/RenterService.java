/*
 * File: RenterService.java
 * Author: Peng Li
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.Property;
import app.withyou.ahometoshare.model.PropertyPicture;
import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.model.form.FilterPropertyForm;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;

import java.util.List;

public interface RenterService {

    public boolean registerRenter(Renter renter);

    public Renter selectRenterByEmail(String email);

    public List<Renter> getAllRenters();

    public Renter selectRenterById(Integer id);

    public boolean updateRenter(Renter renter);

    public boolean validatePassword(String password);

    public boolean updateRenterAccountSettings(UpdateAccountSettingForm form);

    public boolean deleteAccount(String password);

    public List<Property> searchHostPropertiesByConditions(FilterPropertyForm form);

    public Property selectPropertyByPropertyId(Integer propertyId);

    public List<PropertyPicture> getPropertyImageByPropertyId(Integer propertyId);

    public PropertyPicture selectPropertyPictureByPictureId(Integer pictureId);

    public boolean bookPropertyRequest(Renter renter, Property property);
}
