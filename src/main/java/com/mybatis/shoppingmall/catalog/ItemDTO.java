package com.mybatis.shoppingmall.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ItemDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    private String itemId;
    private String productId;
    private BigDecimal listPrice;
    private BigDecimal unitCost;
    private int supplierId;
    private String status;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private ProductDTO product;
    private int quantity;

    public void setItemId(String itemId) {
        this.itemId = itemId.trim();
    }
    public String toString() {
        return "(" + getItemId() + "-" + getProductId() + ")";
    }

}

