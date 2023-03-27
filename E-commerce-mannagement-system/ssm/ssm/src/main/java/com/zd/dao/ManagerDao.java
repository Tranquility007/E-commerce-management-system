package com.zd.dao;

import com.zd.domain.Manager;

public interface ManagerDao {
    Manager findManager(Manager manager);

    Manager findUserName(String username);
}
