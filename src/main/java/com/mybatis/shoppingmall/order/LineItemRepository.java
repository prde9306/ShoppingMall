package com.mybatis.shoppingmall.order;

import java.util.List;

public interface LineItemRepository {

    List<LineItemVO> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItemVO lineItem);
}
