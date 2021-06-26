package com.mybatis.shoppingmall.order;

import com.mybatis.shoppingmall.cart.CartItem;
import com.mybatis.shoppingmall.catalog.ItemDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
@NoArgsConstructor
@Getter
@Setter
public class LineItemVO implements Serializable {

    private static final long serialVersionUID = 6804536240033522156L;
    private int orderId;
    private int lineNumber;
    @Setter(AccessLevel.NONE)
    private int quantity;
    private String itemId;
    private BigDecimal unitPrice;
    @Setter(AccessLevel.NONE)
    private ItemDTO item;
    private BigDecimal total;

    public LineItemVO(int lineNumber, CartItem cartItem) {
        this.lineNumber = lineNumber;
        this.quantity = cartItem.getQuantity();
        this.itemId = cartItem.getItem().getItemId();
        this.unitPrice = cartItem.getItem().getListPrice();
        this.item = cartItem.getItem();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }

    public void setItem(ItemDTO item) {
        this.item = item;
        calculateTotal();
    }

    private void calculateTotal() {
        if (item != null && item.getListPrice() != null) {
            total = item.getListPrice().multiply(new BigDecimal(quantity));
        } else {
            total = null;
        }

    }

}
