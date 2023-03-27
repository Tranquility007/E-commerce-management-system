package com.zd.dao;

import com.zd.domain.Shops;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopsDao {
    //查询所有店铺信息
    List<Shops> findAll();

    //根据店铺名称查询
    List<Shops> findByName(String title);

    //删除店铺
    int delete(String shopId);

    int add(Shops shops);

    Shops findById(String id);

    int update(Shops shop);

    //根据店铺名称查询
    List<Shops> findTitle(String title);
}
