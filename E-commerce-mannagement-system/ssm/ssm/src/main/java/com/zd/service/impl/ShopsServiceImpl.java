package com.zd.service.impl;

import com.github.pagehelper.PageHelper;
import com.zd.utils.Pagers;
import com.zd.dao.ShopsDao;
import com.zd.domain.Shops;
import com.zd.service.IShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopsServiceImpl implements IShopsService{
    @Autowired
    private ShopsDao dao;

    @Override
    public Pagers<Shops> findByName(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<Shops> all = dao.findAll();
        Pagers<Shops> p = new Pagers<>(all);
        return p;
    }

    @Override
    public Shops findTitle(String title) {
        List<Shops> shops = dao.findTitle(title);
        if(shops!=null && shops.size()>0){
            //查询到了店铺
            return shops.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public Pagers<Shops> findByName(String name, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum,pagesize);
        name = "%"+name+"%";
        List<Shops> list = dao.findByName(name);
        Pagers<Shops> p = new Pagers<>(list);
        if(null!=list && list.size()>0){
            //查询成功
            return p;
        }else {
            return null;
        }
    }

    @Override
    public int add(Shops shops) {
        return dao.add(shops);
    }

    @Override
    public Shops findById(String id) {
        return dao.findById(id);
    }

    @Override
    public int update(Shops shop) {
        return dao.update(shop);
    }
}
