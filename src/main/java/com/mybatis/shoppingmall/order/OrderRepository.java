package com.mybatis.shoppingmall.order;

import java.util.List;

public interface OrderRepository {

    List<OrderVO> getOrdersByUsername(String username);

    OrderVO getOrder(int orderId);

    void insertOrder(OrderVO order);

    void insertOrderStatus(OrderVO order);

}
