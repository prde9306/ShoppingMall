package com.mybatis.shoppingmall.catalog;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    List<ProductDTO> getProductListByCategory(String categoryId);
    ProductDTO getProduct(String productId);
    List<ProductDTO> searchProductList(String keywords);

}
