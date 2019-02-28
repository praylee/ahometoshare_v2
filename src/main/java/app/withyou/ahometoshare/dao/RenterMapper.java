package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.Renter;

public interface RenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Renter record);

    int insertSelective(Renter record);

    Renter selectByPrimaryKey(Integer id);

    Renter selectByEmail(String email);

    int updateByPrimaryKeySelective(Renter record);

    int updateByPrimaryKey(Renter record);
}