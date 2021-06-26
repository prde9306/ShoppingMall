package com.mybatis.shoppingmall.order;

import java.util.List;

public interface OrderService {

    void insertOrder(OrderVO order);

    OrderVO getOrder(int orderId);

    List<OrderVO> getOrdersByUsername(String username);

    int getNextId(String name);


}
