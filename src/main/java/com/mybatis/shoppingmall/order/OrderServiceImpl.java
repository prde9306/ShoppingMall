package com.mybatis.shoppingmall.order;

import com.mybatis.shoppingmall.catalog.ItemDTO;
import com.mybatis.shoppingmall.catalog.ItemRepository;
import com.mybatis.shoppingmall.catalog.LineItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private ItemRepository itemRepository;

    private OrderRepository orderRepository;

    private SequenceRepository sequenceRepository;

    private LineItemRepository lineItemRepository;


    @Override
    @Transactional
    public void insertOrder(OrderVO order) {
        order.setOrderId(getNextId("ordernum"));
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItemVO lineItem = (LineItemVO) order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            Map<String, Object> param = new HashMap<String, Object>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            itemRepository.updateInventoryQuantity(param);
        }
        orderRepository.insertOrder(order);
        orderRepository.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItemVO lineItem = (LineItemVO) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemRepository.insertLineItem(lineItem);
        }

    }

    @Override
    public OrderVO getOrder(int orderId) {
        OrderVO order = orderRepository.getOrder(orderId);
        order.setLineItems(lineItemRepository.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItemVO lineItem = (LineItemVO) order.getLineItems().get(i);
            ItemDTO item = itemRepository.getItem(lineItem.getItemId());
            item.setQuantity(itemRepository.getInventoryQuantity(lineItem
                    .getItemId()));
            lineItem.setItem(item);
        }
        return order;
    }

    @Override
    public List<OrderVO> getOrdersByUsername(String username) {
        return orderRepository.getOrdersByUsername(username);
    }

    @Override
    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        sequence = (Sequence) sequenceRepository.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next "
                    + name + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceRepository.updateSequence(parameterObject);
        return sequence.getNextId();
    }
}
