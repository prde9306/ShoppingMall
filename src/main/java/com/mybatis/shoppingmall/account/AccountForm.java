package com.mybatis.shoppingmall.account;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AccountForm {

    public static interface NewAccount {
    }
    public static interface EditAccount {
    }

    @Null(groups = EditAccount.class)
    @NotNull(groups = NewAccount.class)
    @Size(min = 1, max = 25)
    private String username;

    @NotNull
    @Size.List({ @Size(min = 1, max = 25, groups = NewAccount.class),
            @Size(min = 0, max = 25, groups = EditAccount.class) })
    private String password;

    @NotNull
    @Size.List({ @Size(min = 1, max = 25, groups = NewAccount.class),
            @Size(min = 0, max = 25, groups = EditAccount.class) })
    private String repeatedPassword;

    @NotNull
    @Size(min = 1, max = 80)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 80)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 80)
    @Email
    private String email;

    @NotNull
    @Size(min = 1, max = 80)
    private String phone;

    @NotNull
    @Size(min = 1, max = 80)
    private String address1;

    @NotNull
    @Size(min = 1, max = 40)
    private String address2;

    @NotNull
    @Size(min = 1, max = 80)
    private String city;

    @NotNull
    @Size(min = 1, max = 80)
    private String state;

    @NotNull
    @Size(min = 1, max = 20)
    private String zip;

    @NotNull
    @Size(min = 1, max = 20)
    private String country;

    @NotNull
    @Size(min = 1, max = 80)
    private String languagePreference;

    @NotNull
    @Size(min = 1, max = 30)
    private String favouriteCategoryId;

    private boolean listOption;

    private boolean bannerOption;
}
