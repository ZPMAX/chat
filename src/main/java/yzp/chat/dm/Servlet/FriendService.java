package yzp.chat.dm.Servlet;

import org.springframework.stereotype.Service;
import yzp.chat.dm.Model.FriendRelational;
import yzp.chat.dm.repository.FriendRelationalRepository;

import javax.annotation.Resource;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/30 15:21
 * @author:多堕大手笔的萨克
 **/
@Service
public class FriendService {
    @Resource
    FriendRelationalRepository friendRelationalRepository;
    //解除好友关系
    public void deleteRelation(Long curd,Long uid){
        FriendRelational r1=friendRelationalRepository.
                findFriendRelationalsByAidEqualsAndAidtoEquals(
                curd,uid);
        FriendRelational r2=friendRelationalRepository.
                findFriendRelationalsByAidEqualsAndAidtoEquals(uid,curd);
        friendRelationalRepository.delete(r1);
        friendRelationalRepository.delete(r2);

    }

}
