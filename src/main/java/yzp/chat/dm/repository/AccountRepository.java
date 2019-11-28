package yzp.chat.dm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import yzp.chat.dm.Model.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByPhone(String phone);
    List<Account> findAccountsByNameLike(String name);
}
