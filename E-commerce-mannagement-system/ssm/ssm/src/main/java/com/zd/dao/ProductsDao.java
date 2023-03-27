package com.zd.dao;

import com.zd.domain.Products;
import com.zd.dto.ProductsDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsDao {

//    查询所有的产品信息
    List<ProductsDto> findAll();
//    增加商品信息
    int save(Products product);

    Products findById(String productId);

    int update(Products product);

    List<ProductsDto> findByTabId(String tabId);

    List<ProductsDto> findByName(String name);

    int deleteById(String productId);

    Products findByCid(String commodityId);
}