package yzp.chat.dm.Servlet;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Model.AddFriendRequestFullAccount;
import yzp.chat.dm.Model.AddFriendsRequest;
import yzp.chat.dm.Model.FriendRelational;
import yzp.chat.dm.core.exception.BadRequestException;
import yzp.chat.dm.core.exception.ForbiddenException;
import yzp.chat.dm.repository.AccountRepository;
import yzp.chat.dm.repository.AddFriendRequestRepository;
import yzp.chat.dm.repository.FriendRelationalRepository;

import javax.annotation.Resource;
import java.util.*;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/28 16:47
 * @author:多堕大手笔的萨克
 **/
@Service
public class AddFriendRequestService {
    @Resource
    AccountServer accountServer;
    @Resource
    AccountRepository accountRepository;
    @Resource
    AddFriendRequestRepository addFriendRequestRepository;
    @Resource
    FriendRelationalRepository friendRelationalRepository;

    public List<AddFriendRequestFullAccount> getUndisposedList(Long uid) {
        List<AddFriendsRequest> list = addFriendRequestRepository.findAddFriendsRequestByToaidEquals(uid);

        List<AddFriendRequestFullAccount> listRes = new ArrayList<>(list.size());
        List<Long> listAid = new ArrayList<>(list.size());

        for (AddFriendsRequest  request: list) {
            AddFriendRequestFullAccount fullAccount = new AddFriendRequestFullAccount();
            BeanUtils.copyProperties(request,fullAccount);
            listRes.add(fullAccount);
            listAid.add(request.getAid());
        }

        // 填充 查询  account
        List<Account> allById = accountRepository.findAllById(listAid);
        LinkedHashMap<Long,Account> linkedHashMap = new LinkedHashMap<>();
        for (Account account : allById) {
            linkedHashMap.put(account.getId(),account);
        }

        for (AddFriendRequestFullAccount re : listRes) {
            re.setAccount(linkedHashMap.get(re.getAid()));
        }
        return listRes;
    }

    public void applyAdd(Long id, Long toUid, String verifInfo) throws BadRequestException {
        if (Objects.equals(id,toUid)) {
            throw new BadRequestException();
        }
        if (!accountRepository.existsById(id)) {
            throw new BadRequestException();
        }
        AddFriendsRequest request =new AddFriendsRequest();
        request.setAid(id);
        request.setAid(toUid);
        request.setVerifilnfo(verifInfo);
        addFriendRequestRepository.save(request);
    }
    public void reply(Long id,Long requestid,Long opt) throws BadRequestException {
        Optional<AddFriendsRequest> addFriendsRequestOptional= addFriendRequestRepository
                .findById(requestid);
        if (addFriendsRequestOptional.isEmpty()) {
            throw new BadRequestException();
        }
        AddFriendsRequest request =addFriendsRequestOptional.get();
        if (!request.getToaid().equals(id)) {
            throw new ForbiddenException();
        }
        if(request.getOperation()!= null&& request.getOperation()!=0){
            throw  new ForbiddenException();
        }
        //    未操作
        //		0
        //    接受
        //		1
        //    忽略
        //		2
        //    拒绝
        //		3
        switch (opt.intValue()) {
            case 0:
                throw new BadRequestException();
            case 1:
                makeFriendRelationship(request);
                request.setOperation(opt.intValue());
                break;
            case 2:
                request.setOperation(opt.intValue());
            case 3:
                request.setOperation(opt.intValue());
                break;
            default:
                throw new BadRequestException();
        }


    }
    private void makeFriendRelationship(AddFriendsRequest request){
        FriendRelational fromRelational = new FriendRelational();
        FriendRelational toRelational = new FriendRelational();

        fromRelational.setAid(request.getAid());
        fromRelational.setAidto(request.getToaid());

        toRelational.setAid(request.getToaid());
        toRelational.setAidto(request.getAid());

        friendRelationalRepository.save(fromRelational);
        friendRelationalRepository.save(toRelational);
    }
}
