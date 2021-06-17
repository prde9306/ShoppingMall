package com.mybatis.shoppingmall.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequiredArgsConstructor
@Controller
@RequestMapping("/catalog")
public class CatalogCtr {
    private final CatalogSvc catalogSvc;

    @RequestMapping
    public String main(){
        return "catalog/Main";
    }




}
