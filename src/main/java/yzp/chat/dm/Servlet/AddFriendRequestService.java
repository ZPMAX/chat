package yzp.chat.dm.Servlet;

import org.springframework.stereotype.Service;
import yzp.chat.dm.Model.AddFriendsRequest;
import yzp.chat.dm.Model.FriendRelational;
import yzp.chat.dm.core.exception.BadRequsetException;
import yzp.chat.dm.core.exception.ForbiddenException;
import yzp.chat.dm.repository.AccountRepository;
import yzp.chat.dm.repository.AddFriendRequestRepository;
import yzp.chat.dm.repository.FriendRelationalRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public List<AddFriendsRequest> getUndisposedList(Long uid) {
        List<AddFriendsRequest> list = addFriendRequestRepository.findAddFriendsRequestByToaidEquals(uid);
        return list;

    }

    public void applyAdd(Long id, Long toUid, String verifInfo) throws BadRequsetException {
        if (Objects.equals(id,toUid)) {
            throw new BadRequsetException();
        }
        if (!accountRepository.existsById(id)) {
            throw new BadRequsetException();
        }
        AddFriendsRequest request =new AddFriendsRequest();
        request.setAid(id);
        request.setAid(toUid);
        request.setVerifilnfo(verifInfo);
        addFriendRequestRepository.save(request);
    }
    public void reply(Long id,Long requestid,Long opt) throws BadRequsetException {
        Optional<AddFriendsRequest> addFriendsRequestOptional= addFriendRequestRepository
                .findById(requestid);
        if (addFriendsRequestOptional.isEmpty()) {
            throw new BadRequsetException();
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
                throw new BadRequsetException();
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
                throw new BadRequsetException();
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
