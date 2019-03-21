package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Host;
import app.withyou.ahometoshare.model.HostDetail;
import app.withyou.ahometoshare.model.Property;
import app.withyou.ahometoshare.model.PropertyPicture;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

    public boolean insertProperty(Property property);

    public boolean insertPropertyPicture(HttpServletRequest request, int propertyId);

    public List<PropertyPicture> getPropertyImageByPropertyId(Integer propertyId);

}
