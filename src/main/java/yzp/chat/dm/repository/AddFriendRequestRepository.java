package yzp.chat.dm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Model.AddFriendsRequest;

import java.util.List;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/28 17:50
 * @author:多堕大手笔的萨克
 **/
public interface AddFriendRequestRepository extends JpaRepository<AddFriendsRequest,Long> {
    List<AddFriendsRequest> findAddFriendsRequestByToaidEquals(Long toaid);

    @Query(value ="select count(*) from #{#entityName} as t where t.toaid = ?1 and (t.operation = 0 or t.operation is null)")
      Long countAddFriendsRequestsByToaidEqualsAndOperation(Long toaid);
}
