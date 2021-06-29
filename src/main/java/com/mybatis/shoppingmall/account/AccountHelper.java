package com.mybatis.shoppingmall.account;

import com.mybatis.shoppingmall.catalog.CatalogSvc;
import com.mybatis.shoppingmall.catalog.ProductDTO;
import com.mybatis.shoppingmall.user.UserDetails;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountHelper {

    protected final Mapper beanMapper;

    protected final AccountSvc accountSvc;

    protected final CatalogSvc catalogSvc;

    public void newAccount(AccountForm form) {
        Account account = beanMapper.map(form, Account.class);
        accountSvc.insertAccount(account);
    }

    public void editAccount(AccountForm form) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Account account = userDetails.getAccount();

        // does not map "username" to use that of session object
        beanMapper.map(form, account, "accountExcludeUsername");
        accountSvc.updateAccount(account);

        // reflect new value to session object
        beanMapper.map(accountSvc.getAccount(account.getUsername()),
                account);
        List<ProductDTO> myList = catalogSvc.getProductListByCategory(account
                .getFavouriteCategoryId());
        // update MyList
        userDetails.getMyList().clear();
        userDetails.getMyList().addAll(myList);

    }

}
