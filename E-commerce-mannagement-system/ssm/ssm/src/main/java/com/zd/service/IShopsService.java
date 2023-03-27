package com.zd.service;

import com.zd.utils.Pagers;
import com.zd.domain.Shops;

public interface IShopsService {

    Pagers<Shops> findByName(int pagenum, int pagesize);

    Shops findTitle(String title);

    int delete(String id);

    Pagers<Shops> findByName(String name, int pagenum, int pagesize);

    int add(Shops shops);

    Shops findById(String id);

    int update(Shops shop);
}
