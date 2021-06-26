package com.mybatis.shoppingmall.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CatalogSvcImpl implements CatalogSvc{

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;


    @Override
    public List<CategoryDTO> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    @Override
    public CategoryDTO getCategory(String categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    @Override
    public ProductDTO getProduct(String productId) {
        return productRepository.getProduct(productId);
    }

    @Override
    public List<ProductDTO> getProductListByCategory(String categoryId) {
        return productRepository.getProductListByCategory(categoryId);
    }

    @Override
    public List<ProductDTO> searchProductList(String keyword) {
        return productRepository.searchProductList("%" + keyword.toLowerCase()+"%");
    }

    @Override
    public List<ItemDTO> getItemListByProduct(String productId) {
        return itemRepository.getItemListByProduct(productId);
    }

    @Override
    public ItemDTO getItem(String itemId) {
        return itemRepository.getItem(itemId);
    }

    @Override
    public boolean isItemInStock(String itemId) {
        return itemRepository.getInventoryQuantity(itemId) > 0;
    }
}
