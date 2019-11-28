package yzp.chat.dm.Servlet;

import org.springframework.stereotype.Service;
import yzp.chat.dm.Model.AddFriendsRequest;
import yzp.chat.dm.core.exception.BadRequsetException;
import yzp.chat.dm.repository.AccountRepository;
import yzp.chat.dm.repository.AddFriendRequestRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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

    public List<AddFriendsRequest> getUndisposedList(Long uid) {
        List<AddFriendsRequest> list = addFriendRequestRepository.findAddFriendsRequestByToaidEquals(uid);
        return list

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
    public void reply(Long id,Long requestid,Long opt){

    }
}
