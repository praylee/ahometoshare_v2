/*
 * File: HostMapper.java
 * Author: Peng Li
 * Modified By: Oroi Wang
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */


package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.Host;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface HostMapper {

    List<Host> selectAll();

    int deleteByPrimaryKey(Integer hostId);

    int insert(Host record);

    int insertSelective(Host record);

    Host selectByPrimaryKey(Integer hostId);

    Host selectByEmail(String email);

    int updateByPrimaryKeySelective(Host record);

    int updateByPrimaryKey(Host record);

    List countHostGroupByReferralSource();

    Long countHosts();
}