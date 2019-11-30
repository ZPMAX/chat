package yzp.chat.dm.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yzp.chat.dm.Servlet.FriendService;
import yzp.chat.dm.core.security.AppUserDetails;

import javax.annotation.Resource;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/30 15:18
 * @author:多堕大手笔的萨克
 **/
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Resource
    FriendService friendService;
    @DeleteMapping("/{uid}")
    void delete(@AuthenticationPrincipal AppUserDetails appUserDetails,
               @PathVariable Long uid){
        Long crud = appUserDetails.account.getId();
        friendService.deleteRelation(crud,uid);

    }
}
