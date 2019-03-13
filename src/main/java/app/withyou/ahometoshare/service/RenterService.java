package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.model.form.UpdateAccountSettingForm;

import java.util.List;

public interface RenterService {

    public int insertRenter(Renter renter);

    public Renter selectRenterByEmail(String email);

    public List<Renter> getAllRenters();

    public Renter selectRenterById(Integer id);

    public boolean updateRenter(Renter renter);

    public boolean validatePassword(String password);

    public boolean updateRenterAccountSettings(UpdateAccountSettingForm form);

    public boolean deleteAccount(String password);
}
