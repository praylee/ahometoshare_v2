package app.withyou.ahometoshare.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.withyou.ahometoshare.dao.RenterMapper;
import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.service.RenterService;

@Service
public class RenterServiceImpl implements RenterService{

    @Autowired
    private RenterMapper renterMapper;

    @Override
    public int saveRenter(Renter renter) {
        Md5Hash password = new Md5Hash( renter.getPassword(), renter.getEmail());
        renter.setPassword(password.toString());
        return renterMapper.insert(renter);
    }

    @Override
    public Renter selectRenterByEmail(String email) {
        return renterMapper.selectByEmail(email);
    }


}
