package com.mybatis.shoppingmall.cart;

import com.mybatis.shoppingmall.catalog.CatalogSvc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
    }

    public void updateCartQuantities(CartForm cartForm, Cart cart) {
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

    public void removeItemFromCart(String cartItem, Cart cart) {
        cart.removeItemById(cartItem);
    }
}
}
