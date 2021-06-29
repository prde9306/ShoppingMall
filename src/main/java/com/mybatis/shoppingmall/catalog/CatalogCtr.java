package com.mybatis.shoppingmall.catalog;

import com.mybatis.shoppingmall.common.CommonResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog")
public class CatalogCtr {

    protected final CatalogSvc catalogSvc;

    @GetMapping
    public String main() {
        return "catalog/Main";
    }

    @GetMapping("viewCategory")
    public CommonResult <CategoryDTO> viewCategory(@RequestParam("categoryId") String categoryId) {
        //Fish밑에 목록
        List<ProductDTO> productList = catalogSvc
                .getProductListByCategory(categoryId);
        //Fish
       CategoryDTO category = catalogSvc.getCategory(categoryId);

        return CommonResult.success(category);
    }

    @RequestMapping("viewProduct")
    public String viewProduct(@RequestParam("productId") String productId,
                              Model model) {
        List<ItemDTO> itemList = catalogSvc.getItemListByProduct(productId);
        ProductDTO product = catalogSvc.getProduct(productId);
        model.addAttribute("itemList", itemList);
        model.addAttribute("product", product);
        return "catalog/Product";
    }

    @RequestMapping("viewItem")
    public String viewItem(@RequestParam("itemId") String itemId, Model model) {
        ItemDTO item = catalogSvc.getItem(itemId);
        ProductDTO product = item.getProduct();
        model.addAttribute("item", item);
        model.addAttribute("product", product);
        return "catalog/Item";
    }

    @RequestMapping(params = "keyword")
    public String searchProducts(@Validated ProductSearchForm form,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "common/Error";
        }
        String keyword = form.getKeyword().toLowerCase();
        List<ProductDTO> productList = catalogSvc.searchProductList(keyword);
        model.addAttribute("productList", productList);
        return "catalog/SearchProducts";
    }
}


    




