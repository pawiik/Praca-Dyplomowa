package com.pdabrowski.WeatherApp.dao;

import com.pdabrowski.WeatherApp.entity.Account;
import com.pdabrowski.WeatherApp.entity.AccountType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountDAOImplementation implements AccountDAO{

    EntityManager entityManager;

    @Autowired
    public AccountDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Account findById(int id) {
        return null;
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        Query query = entityManager.createQuery(
                "SELECT a FROM Account a WHERE a.accountId = :email", Account.class);
        query.setParameter("email", email);

        Account account = (Account) query.getSingleResult();

        return Optional.ofNullable(account);
    }

    @Transactional
    @Override
    public Account save(Account newAccount) {
       return entityManager.merge(newAccount);
    }

    @Override
    @Transactional
    public void delete(Account account) {
        if (entityManager.contains(account)) {
            entityManager.remove(account);
        } else {
            entityManager.remove(entityManager.merge(account));
        }
    }
}
