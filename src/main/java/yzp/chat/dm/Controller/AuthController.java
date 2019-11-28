package yzp.chat.dm.Controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Model.Token;
import yzp.chat.dm.Servlet.AuthService;
import yzp.chat.dm.core.exception.NotFoundException;
import yzp.chat.dm.core.security.AppUserDetails;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/17 20:38
 * @author:多堕大手笔的萨克
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
       @PostMapping("/signin")
       public Token signin(@Valid @RequestBody SigninPara para) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
            authService.singin(para.getPhone(),para.getPassword());
            return authService.login(para.getPhone(),para.password);
       }
       @PostMapping("/login")
       public Token login(@Valid @RequestBody SigninPara para) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
           Token loginV = authService.login(para.getPhone(), para.getPassword());
           if (loginV == null){
               throw new NotFoundException();
           }
           return loginV;
       }
       @PostMapping("/updatapwd")
    public Account updatapwd(@AuthenticationPrincipal AppUserDetails appUserDetails,
                             @Valid @RequestBody UpdatePwd updatePwd)
               throws UnsupportedEncodingException, InvalidKeyException {
           //Long a=Long.valueOf(updatePwd.getUid());
           return authService.updatePWD(appUserDetails.account.getId(),updatePwd.getOldpassword(),updatePwd.getNewpassword());
       }
}
@Getter
@Setter
class SigninPara {
    @NotBlank(message = "手机号不能为空")
    String phone;
    @NotBlank(message = "密码不能为空")
    String password;
}
@Getter
@Setter
class UpdatePwd {
    @NotBlank()
    String oldpassword;
    @NotBlank()
    String newpassword;
}