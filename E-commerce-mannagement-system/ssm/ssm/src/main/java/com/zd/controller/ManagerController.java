package com.zd.controller;

import com.zd.domain.Manager;
import com.zd.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ManagerController {
    @Autowired
    private IManagerService service;

    @RequestMapping("/findUser")
    public String findUser(Manager manager,Model model){
        //获取界面上的user对象
        String username=manager.getUsername().trim().toString();
        String password=manager.getPassword().trim().toString();
        System.out.println(username+"--"+password);
        //查询数据库中的user对象
        Manager user = service.findUser(manager);
        if(null!=user && !user.equals("")){
            //查询成功
            model.addAttribute("user",user.getUsername());
            return "index";
        }else {
            model.addAttribute("msg","用户名或密码错误");
            return "forward:/index.jsp";
        }
    }

}
