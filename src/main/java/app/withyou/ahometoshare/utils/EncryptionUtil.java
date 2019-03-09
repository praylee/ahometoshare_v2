package app.withyou.ahometoshare.utils;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;

import java.security.Key;

public class EncryptionUtil {

    private static final Key key;
    private static AesCipherService aesCipherService = new AesCipherService();

    static {
        aesCipherService.setKeySize(128);
        key = aesCipherService.generateNewKey();
    }

    public static String EncryptAES(String text){
        return aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
    }

    public static String DecryptAES(String encrptText){
        return  new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
    }


    public static void main(String[] args) {
        String str ="helloworld";
        String e = EncryptionUtil.EncryptAES(str);
        System.out.println(e);
        System.out.println(EncryptionUtil.DecryptAES(e));
    }

}
