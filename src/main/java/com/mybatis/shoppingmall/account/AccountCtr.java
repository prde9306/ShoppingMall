package com.mybatis.shoppingmall.account;

import com.mybatis.shoppingmall.user.UserDetails;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountCtr {

    //이거 어떻게 사용하는지 방법 확인
    protected Mapper beanMapper;

    protected AccountHelper accountHelper;

    protected PasswordEqualsValidator passwordEqualsValidator;

    private static final List<String> LANGUAGE_LIST;

    private static final List<String> CATEGORY_LIST;

    static {
        List<String> langList = new ArrayList<String>();
        langList.add("english");
        langList.add("japanese");
        LANGUAGE_LIST = Collections.unmodifiableList(langList);

        List<String> catList = new ArrayList<String>();
        catList.add("FISH");
        catList.add("DOGS");
        catList.add("REPTILES");
        catList.add("CATS");
        catList.add("BIRDS");
        CATEGORY_LIST = Collections.unmodifiableList(catList);
    }

    @InitBinder("accountForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(passwordEqualsValidator);
    }

    @ModelAttribute("languageList")
    public List<String> getLanguageList() {
        return LANGUAGE_LIST;
    }

    @ModelAttribute("categoryList")
    public List<String> getCategoryList() {
        return CATEGORY_LIST;
    }

    @ModelAttribute
    public AccountForm setUpForm() {
        return new AccountForm();
    }
    //로그인 폼
    @RequestMapping("signonForm")
    public String signonForm() {
        return "account/SignonForm";
    }
    //회원가입 폼
    @RequestMapping("newAccountForm")
    public String newAccountForm() {
        return "account/NewAccountForm";
    }

    @RequestMapping("newAccount")
    public String newAccount(
            @Validated({ AccountForm.NewAccount.class, Default.class }) AccountForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            return "account/NewAccountForm";
        }
        accountHelper.newAccount(form);
        return "redirect:/account/signonForm";
    }

    @RequestMapping("editAccountForm")
    public String editAccountForm(AccountForm form) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Account account = userDetails.getAccount();
        beanMapper.map(account, form);
        //????왜??
        form.setPassword("");
        return "account/EditAccountForm";
    }

    @RequestMapping("editAccount")
    public String editAccount(
            @Validated({ AccountForm.EditAccount.class, Default.class }) AccountForm form,
            BindingResult result) {
        if (result.hasErrors()) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            form.setUsername(userDetails.getUsername());
            return "account/EditAccountForm";
        }
        accountHelper.editAccount(form);
        return "redirect:/account/editAccountForm";
    }

}
