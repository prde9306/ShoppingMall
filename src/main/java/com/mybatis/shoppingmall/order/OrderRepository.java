package com.mybatis.shoppingmall.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderRepository {

    List<OrderVO> getOrdersByUsername(String username);

    OrderVO getOrder(int orderId);

    void insertOrder(OrderVO order);

    void insertOrderStatus(OrderVO order);

}
