package com.zd.service;

import com.zd.dto.TAPDto;
import com.zd.utils.Pagers;
import com.zd.domain.Tabs;

import java.util.List;

public interface ITabsService {
    List<Tabs> findAll();

    Pagers<Tabs> findByPagers(int pagenum, int pagesize);

    int delete(int id);

    Pagers<Tabs> findByName(String name, int pagenum, int pagesize);

    int add(Tabs tab);

    Tabs findById(Integer id);

    int update(Tabs tab);

    Pagers<TAPDto> findCount(int pagenum, int pagesize);

    Tabs findName(String name);
}
