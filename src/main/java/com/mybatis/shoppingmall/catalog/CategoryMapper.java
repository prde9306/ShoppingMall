package com.mybatis.shoppingmall.catalog;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<CategoryDTO> getCategoryList();

    CategoryDTO getCategory(String categoryId);
}
