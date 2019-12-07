package yzp.chat.dm.Servlet;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Model.Token;
import yzp.chat.dm.core.exception.ForbiddenException;
import yzp.chat.dm.core.exception.NotFoundException;
import yzp.chat.dm.repository.AccountRepository;
import yzp.chat.dm.repository.TokenRepository;
import javax.annotation.Resource;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/16 21:13
 * @author:多堕大手笔的萨克
 **/
@Service
public class AuthService {
    @Resource
    AccountRepository accountRepository;
    @Resource
    TokenRepository tokenRepository;
    //hmac加密
    @Resource
    HmacMD5 hmacMD5;

    KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
    SecretKey key = keyGen.generateKey();
    // HmacMD5算法
    Mac mac = Mac.getInstance(key.getAlgorithm());

    public AuthService() throws NoSuchAlgorithmException {
    }

    public Account singin(String phone,String pwd) throws  InvalidKeyException, UnsupportedEncodingException {
        var account=new Account();
        account=accountRepository.findAccountByPhone(phone);
        if(account!=null){
         throw new NotFoundException();
        }
        //HMACMD5加密
        //String pwd1=encryption(pwd);
        String pwd1= DigestUtils.md5DigestAsHex(pwd.getBytes());
        account =new Account();
        account.setName("phone+"+phone);
        account.setPass(pwd1);
        account.setPhone(phone);
        account=accountRepository.save(account);
        return account;
    }
    public Token login(String phone,String password) throws UnsupportedEncodingException, InvalidKeyException {
        Account account=new Account();
        account =accountRepository.findAccountByPhone(phone);
        if(account!=null){
            //HMACMD5加密
             //password=encryption(password);
             password=DigestUtils.md5DigestAsHex(password.getBytes());
            if(account.getPass().equals(password)){
                Token token =new Token();
                token.setAid(account.getId());
                String tokenVa= UUID.randomUUID().toString();
                token.setToken(tokenVa);
                token=tokenRepository.save(token);
                return token;
            }else {
                return null;
            }
        }else {
            throw new NotFoundException();
        }
    }
    public Account updatePWD(Long uid,String oldpassword, String newpassword) throws UnsupportedEncodingException, InvalidKeyException {
        Optional<Account> byId = accountRepository.findById(uid);
        if (byId.isEmpty()){
            throw new NotFoundException();
        }
        Account account = byId.get();
        //oldpassword =encryption(oldpassword);
        oldpassword =DigestUtils.md5DigestAsHex(oldpassword.getBytes());
        if (!account.getPass().equals(oldpassword)) {
            throw new ForbiddenException();
        }
        //newpassword = encryption(newpassword);
        newpassword=DigestUtils.md5DigestAsHex(newpassword.getBytes());
        account.setPass(newpassword);
        account= accountRepository.save(account);
        return account;
    }
    String encryption(String pass) throws InvalidKeyException, UnsupportedEncodingException {
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
