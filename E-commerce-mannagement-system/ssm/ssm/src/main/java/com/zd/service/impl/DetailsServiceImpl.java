package com.zd.service.impl;

import com.github.pagehelper.PageHelper;
import com.zd.dao.DetailsDao;
import com.zd.dto.SAPDto;
import com.zd.service.IDetailsService;
import com.zd.utils.Pagers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsServiceImpl implements IDetailsService{
    @Autowired
    private DetailsDao dao;
    @Override
    public Pagers<SAPDto> findByPagers(String sap, int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        sap = "%"+sap+"%";
        List<SAPDto> all = dao.findSap(sap);
        Pagers<SAPDto> p = new Pagers<>(all);
        return p;
    }
}
