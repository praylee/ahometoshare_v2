/*
 * File: ErrorEnum.java
 * Author: Chen Huang
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

package app.withyou.ahometoshare.utils;

public enum ErrorEnum {
    /*
     * Error message
     * */
    E_400(400, "Fail to process your request. Please try it later"),
    E_500(500, "Unexpected Access Request"),
    E_401(401, "Insufficient Privilege"),
    E_10009(10009, "Account already exist"),
    E_20011(20011, "Expired Session");


    private int errorCode;

    private String errorMsg;

    ErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
