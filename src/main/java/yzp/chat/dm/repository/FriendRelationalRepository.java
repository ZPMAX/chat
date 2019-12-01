package yzp.chat.dm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yzp.chat.dm.Model.FriendRelational;

import java.util.List;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/28 21:53
 * @author:多堕大手笔的萨克
 **/
public interface FriendRelationalRepository extends JpaRepository<FriendRelational,Long> {
    FriendRelational findFriendRelationalsByAidEqualsAndAidtoEquals(Long aid,Long toaid);
     List<FriendRelational> findFriendRelationalsByAidEquals(Pageable pageable,Long aid);
}
