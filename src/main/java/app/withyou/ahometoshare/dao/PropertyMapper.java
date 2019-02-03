package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.Property;

public interface PropertyMapper {
    int deleteByPrimaryKey(Integer propertyId);

    int insert(Property record);

    int insertSelective(Property record);

    Property selectByPrimaryKey(Integer propertyId);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);
}