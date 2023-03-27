package com.zd.service;

import com.zd.domain.Manager;
import org.springframework.stereotype.Service;
public interface IManagerService {

    Manager findByUsername(String username);

    Manager findUser(Manager manager);
}
