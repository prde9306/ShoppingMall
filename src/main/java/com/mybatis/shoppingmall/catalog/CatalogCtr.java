package com.mybatis.shoppingmall.catalog;

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

    @RequestMapping
    public String main() {
        return "catalog/Main";
    }

    @RequestMapping("viewCategory")
    public String viewCategory(@RequestParam("categoryId") String categoryId,
                               Model model) {
        List<ProductDTO> productList = catalogSvc
                .getProductListByCategory(categoryId);
        CategoryDTO category = catalogSvc.getCategory(categoryId);
        model.addAttribute("productList", productList);
        model.addAttribute("category", category);
        return "catalog/Category";
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


    




