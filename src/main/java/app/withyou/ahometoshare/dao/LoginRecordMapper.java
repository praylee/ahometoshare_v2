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