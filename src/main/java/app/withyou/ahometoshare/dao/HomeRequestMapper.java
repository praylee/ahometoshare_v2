package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.HomeRequest;

import java.util.List;

public interface HomeRequestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomeRequest record);

    int insertSelective(HomeRequest record);

    HomeRequest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomeRequest record);

    int updateByPrimaryKey(HomeRequest record);

    List<HomeRequest> selectAll();
}