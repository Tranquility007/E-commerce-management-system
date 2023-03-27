package com.zd.service.impl;

import com.github.pagehelper.PageHelper;
import com.zd.utils.Pagers;
import com.zd.dao.ProductsDao;
import com.zd.domain.Products;
import com.zd.dto.ProductsDto;
import com.zd.service.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements IProductsService{

    @Autowired
    private ProductsDao dao;
    @Override
    public List<ProductsDto> findAll() {
        return dao.findAll();
    }

    @Override
    public int save(Products product) {
        return dao.save(product);
    }

    @Override
    public Products findById(String productId) {
        return dao.findById(productId);
    }

    @Override
    public int update(Products product) {
        return dao.update(product);
    }

    @Override
    public Pagers<ProductsDto> findByTabId(String tabId, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<ProductsDto> all = dao.findByTabId(tabId);
        Pagers<ProductsDto> p = new Pagers<>(all);
        return p;
    }

    @Override
    public Pagers<ProductsDto> findByPagers(ProductsDto products, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<ProductsDto> all = dao.findAll();
        Pagers<ProductsDto> p = new Pagers<>(all);
        return p;
    }

    @Override
    public Pagers<ProductsDto> findByName(String name, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum,pagesize);
        name = "%"+name+"%";
        List<ProductsDto> list = dao.findByName(name);
        Pagers<ProductsDto> p = new Pagers<>(list);
        if(null!=list && list.size()>0){
            //查询成功
            return p;
        }else {
            return null;
        }
    }

    @Override
    public int deleteById(String productId) {
        return dao.deleteById(productId);
    }

    @Override
    public Products findByCid(String commodityId) {
        return dao.findByCid(commodityId);
    }
}
