package yzp.chat.dm.core.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import yzp.chat.dm.Model.Account;

import java.util.ArrayList;
import java.util.Collection;

public class AppUserDetails implements UserDetails {

    public Account  account;

    public AppUserDetails(Account account) {
        this.account = account;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return account.getPass();
    }

    @Override
    public String getUsername() {
        return account.getId().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
