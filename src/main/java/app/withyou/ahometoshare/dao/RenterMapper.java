package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.Renter;

import java.util.List;

public interface RenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Renter record);

    int insertSelective(Renter record);

    Renter selectByPrimaryKey(Integer id);

    Renter selectByEmail(String email);

    int updateByPrimaryKeySelective(Renter record);

    int updateByPrimaryKey(Renter record);

    List<Renter> selectAll();
}