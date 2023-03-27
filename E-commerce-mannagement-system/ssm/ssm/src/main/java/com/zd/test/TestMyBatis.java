package com.zd.test;

import com.zd.dao.ProductsDao;
import com.zd.domain.Products;
import com.zd.dto.ProductsDto;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestMyBatis {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;

    @Before
    public void before() throws Exception{
        // 加载配置文件
        in = Resources.getResourceAsStream("applicationContext.xml");
        // 创建SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        session = factory.openSession();
    }
    @After
    public void after() throws Exception {
        session.close();
        in.close();
    }

    /**
     * 测试查询所有
     * @throws Exception
     */
    @Test
    public void findAll() throws Exception {
//        获取代理对象
        ProductsDao dao = session.getMapper(ProductsDao.class);
//        查询所有的商品信息
        List<ProductsDto> products = dao.findAll();
//        System.out.println(products);
        for(Products p : products){
            System.out.println(p);
        }

    }


    /**
     * 测试保存
     * @throws Exception
     */
    @Test
    public void run2() throws Exception {
//        Account account = new Account();
//        account.setName("熊大");
//        account.setMoney(400d);

        // 加载配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 创建SqlSession对象
        SqlSession session = factory.openSession();
        // 获取到代理对象
//        AccountDao dao = session.getMapper(AccountDao.class);

        // 保存
//        dao.saveAccount(account);

        // 提交事务
        session.commit();

        // 关闭资源
        session.close();
        in.close();
    }

}
