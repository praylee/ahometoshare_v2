/*
 * File: LoginRecordMapper.java
 * Author: Peng Li
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.LoginRecord;

import java.util.List;
import java.util.Map;

public interface LoginRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    LoginRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);

    List<Map> selectHostLoginRecordGroupByMonth();

    List<Map> selectRenterLoginRecordGroupByMonth();

    Long countActiveHosts(Integer period);

    Long countActiveRenters(Integer period);
}