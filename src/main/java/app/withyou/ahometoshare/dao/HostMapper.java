package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.Host;

import java.util.List;

public interface HostMapper {

    List<Host> selectAll();

    int deleteByPrimaryKey(Integer hostId);

    int insert(Host record);

    int insertSelective(Host record);

    Host selectByPrimaryKey(Integer hostId);

    Host selectByEmail(String email);

    int updateByPrimaryKeySelective(Host record);

    int updateByPrimaryKey(Host record);
}