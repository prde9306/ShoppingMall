package com.mybatis.shoppingmall.cart;

import com.mybatis.shoppingmall.common.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("cart")
public class CartCtr {

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
//
//    @RequestMapping("addItemToCart")
//    public String addItemToCart(
//            @RequestParam("workingItemId") String workingItemId) {
//        cartHelper.addItemToCart(workingItemId, cart);
//        return "redirect:/cart/viewCart";
//    }
    @GetMapping("addItemToCart")
    public CommonResult addItemToCart(
            @RequestParam("workingItemId") String workingItemId) {
        //이건 svc거치기 전에 한 단계 더 둬서.. 따라서 Boolen이나 Int로 받을 수가 없는데.. 결과 처리를 어떻게 여기서 해주지?...
        boolean success = cartHelper.addItemToCart(workingItemId, cart);
        if(success){
            return CommonResult.success(null);
        } else{
            return CommonResult.failed();
        }
    }

    @RequestMapping("updateCartQuantities")
    public String updateCartQuantities(CartFormDTO cartForm, Model model) {
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
