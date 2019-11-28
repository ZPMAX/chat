package yzp.chat.dm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Model.AddFriendsRequest;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/28 17:50
 * @author:多堕大手笔的萨克
 **/
public interface AddFriendRequestRepository extends JpaRepository<AddFriendsRequest,Long> {
    Account
}
