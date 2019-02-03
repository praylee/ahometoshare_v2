package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.Renter;

public interface RenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Renter record);

    int insertSelective(Renter record);

    Renter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Renter record);

    int updateByPrimaryKeyWithBLOBs(Renter record);

    int updateByPrimaryKey(Renter record);
}