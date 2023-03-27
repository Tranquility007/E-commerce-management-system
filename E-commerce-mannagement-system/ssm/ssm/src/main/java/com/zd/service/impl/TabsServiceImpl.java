package com.zd.service.impl;

import com.github.pagehelper.PageHelper;
import com.zd.dto.TAPDto;
import com.zd.utils.Pagers;
import com.zd.dao.TabsDao;
import com.zd.domain.Tabs;
import com.zd.service.ITabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabsServiceImpl implements ITabsService{
    @Autowired
    private TabsDao dao;

    @Override
    public List<Tabs> findAll() {
        List<Tabs> tabs = dao.findAll();
        return tabs;
    }

    @Override
    public Pagers<Tabs> findByPagers(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        List<Tabs> all = dao.findAll();
        Pagers<Tabs> p = new Pagers<>(all);
        return p;
    }


    @Override
    public int delete(int id) {
        return dao.delete(id);
    }

    @Override
    public Pagers<Tabs> findByName(String name, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum,pagesize);
        name = "%"+name+"%";
        List<Tabs> list = dao.findByName(name);
        Pagers<Tabs> p = new Pagers<>(list);
        if(null!=list && list.size()>0){
            //查询成功
            return p;
        }else {
            return null;
        }
    }

    @Override
    public int add(Tabs tab) {
        return dao.add(tab);
    }

    @Override
    public Tabs findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public int update(Tabs tab) {
        return dao.update(tab);
    }

    @Override
    public Pagers<TAPDto> findCount(int pagenum, int pagesize) {
        //调用dao层的查询方法
        List<TAPDto> all = dao.findCount();
        //设置分页参数
        PageHelper.startPage(pagenum, pagesize);
        //将对象列表封装到Pagers对象中
        Pagers<TAPDto> p = new Pagers<>(all);
        //返回Pagers对象
        return p;
    }

    @Override
    public Tabs findName(String name) {
        List<Tabs> tabs = dao.findName(name);
        if(tabs!=null && tabs.size()>0){
            //查询到了该商品类型
            return tabs.get(0);
        }else {
            return null;
        }
    }
}
