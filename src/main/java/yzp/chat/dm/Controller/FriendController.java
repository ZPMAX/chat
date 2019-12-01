package yzp.chat.dm.Controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Servlet.AccountServer;
import yzp.chat.dm.Servlet.FriendService;
import yzp.chat.dm.core.security.AppUserDetails;
import yzp.chat.dm.repository.AccountRepository;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    AccountServer accountServer;
    @Resource
    AccountRepository accountRepository;
    @DeleteMapping("/{uid}")
    void delete(@AuthenticationPrincipal AppUserDetails appUserDetails,
               @PathVariable Long uid){
        Long crud = appUserDetails.account.getId();
        friendService.deleteRelation(crud,uid);
    }
    @GetMapping("/")
    List<Account> index(@AuthenticationPrincipal AppUserDetails appUserDetails,
                        @RequestParam(defaultValue = "0") Integer page,
                        @RequestParam(defaultValue = "0") Integer size){
        Sort sort=Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page,size,sort);
        return friendService.findAll(pageable,appUserDetails.account.getId());

    }
    @GetMapping("/{uid}")
    Account get(@PathVariable Long uid){
        return friendService.findFriend(uid);
    }
}
