package com.zd.service;


import com.zd.utils.Pagers;
import com.zd.domain.Products;
import com.zd.dto.ProductsDto;

import java.util.List;

public interface IProductsService{
//    查询所有的商品信息
    List<ProductsDto> findAll();

    int save(Products product);

    Products findById(String productId);

    int update(Products product);

    Pagers<ProductsDto> findByTabId(String tabId, int pagenum, int pagesize);

    Pagers<ProductsDto> findByPagers(ProductsDto products, int pagenum, int pagesize);

    Pagers<ProductsDto> findByName(String name, int pagenum, int pagesize);

    int deleteById(String productId);

    Products findByCid(String commodityId);
}
