package com.mybatis.shoppingmall.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CategoryDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    private String categoryId;
    private String name;
    private String description;

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId.trim();
    }
    public String toString() {
        return getCategoryId();
    }
}
