package yzp.chat.dm.Servlet;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Model.FriendRelational;
import yzp.chat.dm.repository.AccountRepository;
import yzp.chat.dm.repository.FriendRelationalRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    AccountServer accountServer;
    @Resource
    AccountRepository accountRepository;
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
    public Account findFriend(Long uid){
        Account account = accountServer.getAccount(uid);
        return account;
    }
    public List<Account> findAll(Pageable pageable,Long aid){
        List<FriendRelational> relationals =friendRelationalRepository.findFriendRelationalsByAidEquals(pageable,aid);
        List<Long> toaidlist = new ArrayList<>();
        for (FriendRelational relational : relationals) {
             Long aidto = relational.getAidto();
             toaidlist.add(aidto);
        }
        List<Account> res = accountRepository.findAllById(toaidlist);
        return res;
    }

}
