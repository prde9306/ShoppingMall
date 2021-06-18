package com.mybatis.shoppingmall.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog")
public class CatalogCtr {
    private final CatalogSvc catalogSvc;

    @GetMapping
    public String main(){
        return "catalog/Main";
    }

    @GetMapping("/viewCategory")
    public CategoryDTO viewCategory(@RequestParam("categoryId") String categoryId){
//
//        List<ProductDTO> productList = catalogSvc
//                .getProductListByCategory(categoryId);
//        CategoryDTO category = catalogSvc.getCategory(categoryId);

        //2개는 어떻게 넘기지??
        return catalogSvc.getCategory(categoryId);

    }


    



}
