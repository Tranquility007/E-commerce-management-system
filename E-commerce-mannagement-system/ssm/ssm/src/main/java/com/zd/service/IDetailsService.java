package com.zd.service;

import com.zd.dto.SAPDto;
import com.zd.utils.Pagers;

public interface IDetailsService {
    Pagers<SAPDto> findByPagers(String sap, int pagenum, int pagesize);
}
