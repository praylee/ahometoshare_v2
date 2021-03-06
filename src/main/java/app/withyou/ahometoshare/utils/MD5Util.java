/*
 * File: MD5Util.java
 * Author: Chen Huang
 * Clients: Michelle Bilek - A Home To Share
 * Course: CST8334 Software Development Project - 2019W
 * Professor: Reg Dyer
 * Project: A Home to Share
 * Copyright @ 2019
 */

package app.withyou.ahometoshare.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {
    public static String encryptWithMD5(String target, String salt){
        Md5Hash md5Hash = new Md5Hash(target,salt, Constants.MD5_ITERATION_TIMES) ;  // 加密，加盐
        return md5Hash.toString();
    }


    public static void main(String[] args) {
        System.out.println(encryptWithMD5("ahometoshare", "prayleee@gmail.com"));
    }
}
