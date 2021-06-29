package com.mybatis.shoppingmall.order;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@Mapper
public interface LineItemRepository {

    List<LineItemVO> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItemVO lineItem);
}
