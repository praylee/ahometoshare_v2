package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.PropertyPicture;

public interface PropertyPictureMapper {
    int deleteByPrimaryKey(Integer pictureId);

    int insert(PropertyPicture record);

    int insertSelective(PropertyPicture record);

    PropertyPicture selectByPrimaryKey(Integer pictureId);

    int updateByPrimaryKeySelective(PropertyPicture record);

    int updateByPrimaryKeyWithBLOBs(PropertyPicture record);

    int updateByPrimaryKey(PropertyPicture record);
}