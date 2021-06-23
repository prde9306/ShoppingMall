package com.mybatis.shoppingmall.account;

public interface AccountSvc {

    Account getAccount(String username);
    Account getAccount(String username, String password);
    void insertAccount(Account account);
    void updateAccount(Account account);

}
