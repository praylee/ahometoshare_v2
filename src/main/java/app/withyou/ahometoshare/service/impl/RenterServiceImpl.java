package app.withyou.ahometoshare.service.impl;

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
        return renterMapper.insert(renter);
    }

    

    
}
