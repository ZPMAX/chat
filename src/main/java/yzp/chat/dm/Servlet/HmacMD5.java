package yzp.chat.dm.Servlet;

import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/22 23:14
 * @author:多堕大手笔的萨克
 **/
@Service
public class HmacMD5 {
    String encryption(String pass) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGen.generateKey();
        // HmacMD5算法
        Mac mac = Mac.getInstance(key.getAlgorithm());
        mac.init(key);
        // 加密内容
        byte[] utf8Str = pass.getBytes("UTF-8");
        // 加密处理
        byte[] digest = mac.doFinal(utf8Str);
        byte[] encode = Base64.getEncoder().encode(digest);
        String ooString=new String(encode,"UTF-8");
        return ooString;
    }
}
