package com.mybatis.shoppingmall.catalog;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemMapper {

    void updateInventoryQuantity(Map<String, Object> param);
    int getInventoryQuantity(String itemId);
    List<ItemDTO> getItemListByProduct(String productId);
    ItemDTO getItem(String itemId);
}
