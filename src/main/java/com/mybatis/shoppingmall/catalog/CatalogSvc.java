package com.mybatis.shoppingmall.catalog;

import java.util.List;

public interface CatalogSvc {

    List<CategoryDTO> getCategoryList();

    CategoryDTO getCategory(String categoryId);

    ProductDTO getProduct(String productId);

    List<ProductDTO> getProductListByCategory(String categoryId);

    // TODO enable using more than one keyword
    List<ProductDTO> searchProductList(String keyword);

    List<ItemDTO> getItemListByProduct(String productId);

    ItemDTO getItem(String itemId);

    boolean isItemInStock(String itemId);
}
