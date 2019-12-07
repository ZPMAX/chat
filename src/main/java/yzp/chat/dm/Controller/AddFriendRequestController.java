package yzp.chat.dm.Controller;

import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Model.AddFriendRequestFullAccount;
import yzp.chat.dm.Model.AddFriendsRequest;
import yzp.chat.dm.Servlet.AccountServer;
import yzp.chat.dm.Servlet.AddFriendRequestService;
import yzp.chat.dm.core.exception.BadRequestException;

import yzp.chat.dm.core.security.AppUserDetails;
import yzp.chat.dm.repository.FriendRelationalRepository;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/28 16:38
 * @author:多堕大手笔的萨克
 **/
@RestController
@RequestMapping("/addfriendrequest")
public class AddFriendRequestController {
    @Resource
    AddFriendRequestService addFriendRequestService;
    @Resource
    AccountServer accountServer;
    @Resource
    FriendRelationalRepository friendRelationalRepository;

    @GetMapping("/")
    List<AddFriendRequestFullAccount> index(@AuthenticationPrincipal AppUserDetails appUserDetails) {
        // 判断是不是当前用户
        return addFriendRequestService.getUndisposedList(appUserDetails.account.getId());
    }
    @GetMapping("/find")
    List<Account> find(String name){
        return accountServer.findUsersByName(name);
    }
    @PostMapping("/apply")
    void apply(@AuthenticationPrincipal AppUserDetails appUserDetails,
               @Valid @RequestBody  ApplyPara applyPara) throws BadRequestException {
        addFriendRequestService.applyAdd(appUserDetails.account.getId(),
                applyPara.getToUid(),
                applyPara.getVerifInfo());
    }

}
@Data
class ApplyPara{
    @NonNull
    Long toUid;
    @NonNull
    String verifInfo;
}
@Data
class ReplyPara{
    @NonNull
    Long requestid;
    @NonNull
    @Max(3)
    @Min(0)
    Long opt;
}

