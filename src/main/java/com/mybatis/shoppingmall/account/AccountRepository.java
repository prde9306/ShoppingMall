package com.mybatis.shoppingmall.account;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Mapper
public interface AccountRepository {

    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(String username, String password);

    void insertAccount(Account account);

    void insertProfile(Account account);

    void insertSignon(Account account);

    void updateAccount(Account account);

    void updateProfile(Account account);

    void updateSignon(Account account);

}
