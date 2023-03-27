package com.zd.dao;

import com.zd.domain.Tabs;
import com.zd.dto.TAPDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface TabsDao {
    /**
     * 查找所有有效的商品类别id
     * @return
     */
    List<Tabs> findAll();

    int delete(int id);

    List<Tabs> findByName(String name);

    int add(Tabs tab);

    Tabs findById(Integer id);

    int update(Tabs tab);

    List<TAPDto> findCount();

    List<Tabs> findName(String name);
}
