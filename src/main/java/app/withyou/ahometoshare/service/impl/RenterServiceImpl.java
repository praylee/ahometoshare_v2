package app.withyou.ahometoshare.service.impl;

import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.withyou.ahometoshare.dao.RenterMapper;
import app.withyou.ahometoshare.model.Renter;
import app.withyou.ahometoshare.service.RenterService;

import java.util.List;

@Service
public class RenterServiceImpl implements RenterService{

    @Autowired
    private RenterMapper renterMapper;

    @Autowired
    private UserService userService;

    @Override
    public int saveRenter(Renter renter) {
        User user = userService.findUserByEmail(renter.getEmail());
        if(user!=null){
            return 0;
        }
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


}
