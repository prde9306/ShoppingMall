package com.mybatis.shoppingmall.catalog;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select({"<script>",
            "SELECT CATID AS categoryId, Name, DESCN AS description",
            "FROM CATEGORY",
            "</script>"})
    List<CategoryDTO> getCategoryList();

    @Select({"<script>",
            " SELECT CATID AS categoryId, NAME, DESCN AS description FROM CATEGORY",
            " WHERE CATID = #{categoryId}",
            "</script>"})
    CategoryDTO getCategory(String categoryId);
}
