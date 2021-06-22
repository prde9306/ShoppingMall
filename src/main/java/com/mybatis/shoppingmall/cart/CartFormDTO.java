package com.mybatis.shoppingmall.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class CartFormDTO {
    private Map<String, Integer> quantity;

}
