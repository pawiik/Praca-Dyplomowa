package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Account;

import java.util.Optional;

public interface AccountDAO {

    Account findById(int id);
    Optional<Account> findByEmail(String email);

    Account save(Account newAccount);

    void delete(Account account);
}
