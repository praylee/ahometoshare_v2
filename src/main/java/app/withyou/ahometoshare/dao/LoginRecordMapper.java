package app.withyou.ahometoshare.dao;

import app.withyou.ahometoshare.model.LoginRecord;

public interface LoginRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginRecord record);

    int insertSelective(LoginRecord record);

    LoginRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginRecord record);

    int updateByPrimaryKey(LoginRecord record);
}