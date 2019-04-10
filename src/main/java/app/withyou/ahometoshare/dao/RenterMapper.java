/*
 * File: RenterMapper.java
 * Author: Peng Li
 * Modified By: Chen Huang
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.Renter;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Renter record);

    int insertSelective(Renter record);

    Renter selectByPrimaryKey(Integer id);

    Renter selectByEmail(String email);

    int updateByPrimaryKeySelective(Renter record);

    int updateByPrimaryKey(Renter record);

    List<Renter> selectAll();

    List countRenterGroupByReferralSource();

    Long countRenters();
}