package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.PropertyPicture;

import java.util.List;

public interface PropertyPictureMapper {
    int deleteByPrimaryKey(Integer pictureId);

    int deleteByPropertyId(Integer propertyId);

    int insert(PropertyPicture record);

    int insertSelective(PropertyPicture record);

    PropertyPicture selectByPrimaryKey(Integer pictureId);

    int updateByPrimaryKeySelective(PropertyPicture record);

    int updateByPrimaryKeyWithBLOBs(PropertyPicture record);

    int updateByPrimaryKey(PropertyPicture record);

    List<PropertyPicture> selectByPropertyId(Integer propertyId);
}