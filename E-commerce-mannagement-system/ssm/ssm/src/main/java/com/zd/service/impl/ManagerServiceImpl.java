package com.zd.service.impl;

import com.zd.dao.ManagerDao;
import com.zd.domain.Manager;
import com.zd.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements IManagerService{
    @Autowired
    private ManagerDao dao;
    @Override
    public Manager findByUsername(String username) {
        return dao.findUserName(username);
    }

    @Override
    public Manager findUser(Manager manager) {
        return dao.findManager(manager);
    }
}
