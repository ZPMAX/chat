package yzp.chat.dm.core.security;

import yzp.chat.dm.Model.Account;
import yzp.chat.dm.Servlet.AccountServer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AppUserDetailsService implements UserDetailsService {


    @Resource
    AccountServer accountService;

    /**
     *
     * @param username 泛指能代表 user 的东西  比如 uid  username  token
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //
        Account account = accountService.getAccount(Long.valueOf(username));

        if(account == null){
            throw new UsernameNotFoundException(username);
        }

        return new AppUserDetails(account);
    }



}


