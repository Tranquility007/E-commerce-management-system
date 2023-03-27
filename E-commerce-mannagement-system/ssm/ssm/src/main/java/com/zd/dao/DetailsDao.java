package com.zd.dao;

import com.zd.dto.SAPDto;
import com.zd.utils.Pagers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsDao {
    List<SAPDto> findSap(String sap);
}
