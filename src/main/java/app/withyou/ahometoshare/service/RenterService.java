package app.withyou.ahometoshare.service;

import app.withyou.ahometoshare.model.Renter;

public interface RenterService {

    public int saveRenter(Renter renter);

    public Renter selectRenterByEmail(String email);

}
