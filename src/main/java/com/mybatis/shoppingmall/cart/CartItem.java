package com.mybatis.shoppingmall.cart;

import com.mybatis.shoppingmall.catalog.ItemDTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {

    private ItemDTO item;
    private int quantity;
    private boolean inStock;
    private BigDecimal total;

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
        calculateTotal();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateTotal();
    }

    public void incrementQuantity() {
        quantity++;
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
