package com.mybatis.shoppingmall.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ProductDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    private String productId;
    private String categoryId;
    private String name;
    private String description;

    public void setProductId(String productId) {
        this.productId = productId.trim();
    }
    public String toString() {
        return getName();
    }
}
