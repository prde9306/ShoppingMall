package com.mybatis.shoppingmall.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("cart")
public class CartController {

    protected CartHelper cartHelper;
    //필요 없을거 같은데? CartHelper랑 연결되서 그런가?
    //protected CatalogService catalogService;
    protected CartVO cart;

    @ModelAttribute
    public CartFormDTO setUpForm() {
        return new CartFormDTO();
    }

    @ModelAttribute
    public CartVO getCart() {
        return cart;
    }

    @RequestMapping("viewCart")
    public String viewCart() {
        return "cart/Cart";
    }

    @RequestMapping("addItemToCart")
    public String addItemToCart(
            @RequestParam("workingItemId") String workingItemId) {
        cartHelper.addItemToCart(workingItemId, cart);
        return "redirect:/cart/viewCart";
    }

    @RequestMapping("updateCartQuantities")
    public String updateCartQuantities(CartForm cartForm, Model model) {
        cartHelper.updateCartQuantities(cartForm, cart);
        return "redirect:/cart/viewCart";
    }

    @RequestMapping("removeItemFromCart")
    public String removeItemFromCart(@RequestParam("cartItem") String cartItem) {
        cart.removeItemById(cartItem);
        return "redirect:/cart/viewCart";
    }

    @RequestMapping("checkOut")
    public String checkOut() {
        return "cart/Checkout";
    }

}
