/*
 * File: PropertyPictureMapper.java
 * Author: Peng Li
 * Modified By: Oroi Wang
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

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