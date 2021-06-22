package com.mybatis.shoppingmall.catalog;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductSearchForm {
    //이렇게 붙이는 거 뭐지?
    @NotNull(message = "Please enter a keyword to search for, then press the search button.")
    @Size(min = 1, message = "Please enter a keyword to search for, then press the search button.")
    private String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
