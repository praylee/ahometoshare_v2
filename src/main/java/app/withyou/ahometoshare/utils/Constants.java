package app.withyou.ahometoshare.utils;

import org.apache.shiro.crypto.AesCipherService;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Constants {
    public static final String SUCCESS_CODE = "100";
    public static final String SUCCESS_MSG = "SUCCESS";
    public static final String FAILURE_MSG = "FAILURE";
    public static final String SESSION_USER = "userInfo";
    public static final String SESSION_USER_PERMISSION = "userPermission";
    public static final String SESSION_USER_NAME = "username";
    public static final String SESSION_USER_PASSWORD = "password";
    public static final Integer USER_TYPE_ADMIN = 0;
    public static final Integer USER_TYPE_HOST = 1;
    public static final Integer USER_TYPE_RENTER = 2;
    public static final String USER_TYPE_STRING = "userType";

    public static final int MD5_ITERATION_TIMES = 1;


    public static final List<String> CITY_lIST;

    static {
        ArrayList<String> list = new ArrayList<>();
        String[] values = {"Toronto","Hamilton","Mississauga","Peel"};
        Collections.addAll(list, values);
        CITY_lIST = list;
    }

}
