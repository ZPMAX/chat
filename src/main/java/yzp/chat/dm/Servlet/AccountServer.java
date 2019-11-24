package yzp.chat.dm.Servlet;

import org.springframework.stereotype.Service;
import yzp.chat.dm.Model.Account;
import yzp.chat.dm.core.AppBeanUtils;
import yzp.chat.dm.core.exception.NotFoundException;
import yzp.chat.dm.repository.AccountRepository;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * ClassName:
 * PackgeName:
 *
 * @date:2019/11/16 15:34
 * @author:多堕大手笔的萨克
 **/
@Service
public class AccountServer {
    @Resource
    AccountRepository accountRepository;
    public Account getAccount(Long id){
        Optional<Account> byid=accountRepository.findById(id);
        if (byid==null) {
            throw  new NotFoundException();
        }
        return  byid.get();
    }
    public Account updateAccount(Long id,Account account){
        Optional<Account> byId=accountRepository.findById(id);
        if(byId==null){
            throw new NotFoundException();
        }
        Account account1=byId.get();
        account.setPass(null);
        AppBeanUtils.copyNotNullProperties(account,account1);
        accountRepository.save(account1);
        return account1;
    }
}
