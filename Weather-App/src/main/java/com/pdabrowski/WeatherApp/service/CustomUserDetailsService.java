package com.pdabrowski.WeatherApp.service;

import com.pdabrowski.WeatherApp.dao.AccountDAO;
import com.pdabrowski.WeatherApp.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountDAO accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username).orElse(null);


        assert account != null;
        String authority = account.getEmployee() != null ? "ROLE_EMPLOYEE" : "ROLE_USER";

        return User.builder()
                .username(username)
                .password(account.getPasswordHash()) // Password should be already hashed
                .authorities(authority)
                .build();
    }
}