package com.mybatis.shoppingmall.order;

import com.mybatis.shoppingmall.account.Account;
import com.mybatis.shoppingmall.cart.CartVO;
import com.mybatis.shoppingmall.user.UserDetails;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderHelper {

    protected OrderService orderService;

    protected Mapper beanMapper;

    public OrderVO newOrder(OrderForm orderForm, CartVO cart) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        Account account = userDetails.getAccount();

        OrderVO order = new OrderVO();
        order.initOrder(account, cart);
        beanMapper.map(orderForm, order);
        orderService.insertOrder(order);
        return order;
    }
}
