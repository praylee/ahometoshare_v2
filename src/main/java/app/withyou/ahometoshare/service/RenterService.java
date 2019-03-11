package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Renter;

import java.util.List;

public interface RenterService {

    public int saveRenter(Renter renter);

    public Renter selectRenterByEmail(String email);

    public List<Renter> getAllRenters();

    public Renter selectRenterById(Integer id);

}
