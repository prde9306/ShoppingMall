package com.mybatis.shoppingmall.account;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class AccountSvcImpl implements AccountSvc {

    private AccountRepository accountRepository;

    /*
     * (non-Javadoc)
     * @see ik.am.jpetstore.domain.service.account.AccountService#getAccount(java.lang.String)
     */
    @Override
    public Account getAccount(String username) {
        return accountRepository.getAccountByUsername(username);
    }

    /*
     * (non-Javadoc)
     * @see ik.am.jpetstore.domain.service.account.AccountService#getAccount(java.lang.String, java.lang.String)
     */
    @Override
    public Account getAccount(String username, String password) {
        return accountRepository.getAccountByUsernameAndPassword(username,
                password);
    }

    /*
     * (non-Javadoc)
     * @see ik.am.jpetstore.domain.service.account.AccountService#insertAccount(ik.am.jpetstore.domain.model.Account)
     */
    @Override
    @Transactional
    public void insertAccount(Account account) {
        accountRepository.insertAccount(account);
        accountRepository.insertProfile(account);
        accountRepository.insertSignon(account);
    }

    /*
     * (non-Javadoc)
     * @see ik.am.jpetstore.domain.service.account.AccountService#updateAccount(ik.am.jpetstore.domain.model.Account)
     */
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
