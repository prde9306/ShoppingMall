package com.mybatis.shoppingmall.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountSvcImpl implements AccountSvc {

    private AccountRepository accountRepository;


    @Override
    public Account getAccount(String username) {
        return accountRepository.getAccountByUsername(username);
    }

    @Override
    public Account getAccount(String username, String password) {
        return accountRepository.getAccountByUsernameAndPassword(username,
                password);
    }

    @Override
    @Transactional
    public void insertAccount(Account account) {
        accountRepository.insertAccount(account);
        accountRepository.insertProfile(account);
        accountRepository.insertSignon(account);
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {
        accountRepository.updateAccount(account);
        accountRepository.updateProfile(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountRepository.updateSignon(account);
        }
    }
}
