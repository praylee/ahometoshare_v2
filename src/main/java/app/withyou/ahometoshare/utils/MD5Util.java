package app.withyou.ahometoshare.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {
    public static String encryptWithMD5(String target, String salt){
        Md5Hash md5Hash = new Md5Hash(target,salt, Constants.MD5_ITERATION_TIMES) ;  // 加密，加盐
        return md5Hash.toString();
    }
}
