package com.mybatis.shoppingmall.cart;

import com.mybatis.shoppingmall.catalog.CatalogSvc;
import com.mybatis.shoppingmall.catalog.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@RequiredArgsConstructor
@Component
public class CartHelper {
    protected CatalogSvc catalogSvc;

    public void addItemToCart(String workingItemId, CartVO cart) {
        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            // isInStock is a "real-time" property that must be updated
            // every time an item is added to the cart, even if other
            // item details are cached.
            boolean isInStock = catalogSvc.isItemInStock(workingItemId);
            ItemDTO item = catalogSvc.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
    }

    public void updateCartQuantities(CartFormDTO cartForm, CartVO cart) {
        Iterator<CartItem> cartItems = cart.getAllCartItems();
        while (cartItems.hasNext()) {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                int quantity = cartForm.getQuantity().get(itemId);
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                }
            } catch (Exception e) {
                // ignore parse exceptions on purpose
            }
        }
    }

    public void removeItemFromCart(String cartItem, CartVO cart) {
        cart.removeItemById(cartItem);
    }
}

