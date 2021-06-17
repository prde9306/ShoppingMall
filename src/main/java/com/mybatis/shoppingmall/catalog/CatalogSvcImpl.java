package com.mybatis.shoppingmall.catalog;

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class CatalogSvcImpl implements CatalogSvc{

    private final CategoryMapper categoryMapper;
    private final ItemMapper itemMapper;
    private final ProductMapper productMapper;


    @Override
    public List<CategoryDTO> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public CategoryDTO getCategory(String categoryId) {
        return categoryMapper.getCategory(categoryId);
    }

    @Override
    public ProductDTO getProduct(String productId) {
        return productMapper.getProduct(productId);
    }

    @Override
    public List<ProductDTO> getProductListByCategory(String categoryId) {
        return productMapper.getProductListByCategory(categoryId);
    }

    @Override
    public List<ProductDTO> searchProductList(String keyword) {
        return productMapper.searchProductList("%" + keyword.toLowerCase()+"%");
    }

    @Override
    public List<ItemDTO> getItemListByProduct(String productId) {
        return itemMapper.getItemListByProduct(productId);
    }

    @Override
    public ItemDTO getItem(String itemId) {
        return itemMapper.getItem(itemId);
    }

    @Override
    public boolean isItemInStock(String itemId) {
        return itemMapper.getInventoryQuantity(itemId) > 0;
    }
}
