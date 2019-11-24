package yzp.chat.dm.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Servlet.AccountServer;
import yzp.chat.dm.core.exception.ForbiddenException;
import yzp.chat.dm.core.security.AppUserDetails;

import javax.annotation.Resource;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/22 21:43
 * @author:多堕大手笔的萨克
 **/
@RestController
@RequestMapping("/mine")
public class MineController {
    @Resource
    AccountServer accountServer;
    @GetMapping("/{uid}")
    Account account(@PathVariable Long uid, @AuthenticationPrincipal AppUserDetails appUserDetails){
        System.out.println(appUserDetails);
        //判断是不是当前用户
        if(!uid.equals(appUserDetails.account.getId())){
            throw new ForbiddenException();
        }
        return accountServer.getAccount(uid);
    }
    @PutMapping("/{uid}")
    Account updateAccount(@PathVariable Long uid,
                          @AuthenticationPrincipal AppUserDetails appUserDetails,
                          @RequestBody Account account
    ){
        if(!uid.equals(appUserDetails.account.getId())){
            throw new ForbiddenException();
        }
        return accountServer.updateAccount(uid,account);
    }
}
