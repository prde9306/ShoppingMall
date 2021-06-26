package com.mybatis.shoppingmall.order;

import com.mybatis.shoppingmall.account.Account;
import com.mybatis.shoppingmall.cart.CartVO;
import com.mybatis.shoppingmall.user.UserDetails;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
//찾아 처음봄, 이걸 통해 카트에 있는 거 ~~ 가져오는 듯
@SessionAttributes("scopedTarget.cart")
public class OrderCtr {

    private static final List<String> CARD_TYPE_LIST;
    protected OrderHelper orderHelper;
    protected OrderService orderService;
    protected Mapper beanMapper;
    protected CartVO cart;

    static {
        List<String> cardList = new ArrayList<String>();
        cardList.add("Visa");
        cardList.add("MasterCard");
        cardList.add("American Express");
        CARD_TYPE_LIST = Collections.unmodifiableList(cardList);
    }

    @ModelAttribute
    public OrderForm setUpForm() {
        return new OrderForm();
    }

    @ModelAttribute("creditCardTypes")
    public List<String> getCardTypeList() {
        return CARD_TYPE_LIST;
    }

    @RequestMapping("newOrderForm")
    public String newOrderForm(OrderForm orderForm, Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Account account = userDetails.getAccount();

        OrderVO order = new OrderVO();
        order.initOrder(account, cart);
        beanMapper.map(order, orderForm);
        model.addAttribute(order);

        return "order/NewOrderForm";
    }

    @RequestMapping(value = "newOrder")
    public String confirmOrder(OrderForm orderForm, OrderVO order) {
        return "order/ConfirmOrder";
    }

    @RequestMapping(value = "newOrder", params = "shippingAddressRequired=true")
    public String shippingForm(OrderForm orderForm, OrderVO order) {
        return "order/ShippingForm";
    }

    @RequestMapping(value = "newOrder", params = "confirmed")
    public String newOrder(OrderForm orderForm, SessionStatus status,
                           RedirectAttributes attributes) {
        OrderVO order = orderHelper.newOrder(orderForm, cart);

        attributes.addAttribute("orderId", order.getOrderId());
        attributes.addFlashAttribute("message",
                "Thank you, your order has been submitted.");
        status.setComplete();
        return "redirect:/order/viewOrder";
    }

    @RequestMapping("viewOrder")
    public String viewOrder(@RequestParam("orderId") int orderId, Model model) {
        OrderVO order = orderService.getOrder(orderId);

        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Account account = userDetails.getAccount();
        if (account.getUsername().equals(order.getUsername())) {
            model.addAttribute(order);
            return "order/ViewOrder";
        } else {
            // TODO
            model.addAttribute("You may only view your own orders.");
            return "common/Error";
        }
    }

    @RequestMapping("listOrders")
    public String listOrders(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        List<OrderVO> orderList = orderService.getOrdersByUsername(username);
        model.addAttribute("orderList", orderList);
        return "order/ListOrders";
    }


}
