package app.withyou.ahometoshare.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.withyou.ahometoshare.dao.RenterMapper;
import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.service.RenterService;

import java.util.List;

@Service
public class RenterServiceImpl implements RenterService{

    Logger logger = LoggerFactory.getLogger(RenterService.class);

    @Autowired
    private RenterMapper renterMapper;

    @Override
    public int insertRenter(Renter renter) {
        Md5Hash password = new Md5Hash( renter.getPassword(), renter.getEmail());
        renter.setPassword(password.toString());
        return renterMapper.insert(renter);
    }

    @Override
    public Renter selectRenterByEmail(String email) {
        return renterMapper.selectByEmail(email);
    }

    @Override
    public List<Renter> getAllRenters() {
        List<Renter> renters =  renterMapper.selectAll();
        renters.stream().forEach(renter -> renter.setPassword(""));
        return renters;
    }

    @Override
    public Renter selectRenterById(Integer id) {
        return renterMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateRenter(Renter renter) {
        try {
            renterMapper.updateByPrimaryKeySelective(renter);
        }catch (Exception e){
            logger.error("Fail to update Renter ",e);
            return false;
        }
        return true;
    }


}
