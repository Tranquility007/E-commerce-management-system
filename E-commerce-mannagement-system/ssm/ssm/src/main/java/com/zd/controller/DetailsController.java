package com.zd.controller;

import com.zd.dto.SAPDto;
import com.zd.service.IDetailsService;
import com.zd.utils.Pagers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/details")
public class DetailsController {
    @Autowired
    private IDetailsService service;

    @RequestMapping("toselect")
    public String toSelect(){
        return "details_list";
    }

    @RequestMapping("findByPagers")
    public String findByPagers(@RequestParam("name")String sap, Integer index, Model model){
//        System.out.println("店铺名称"+sap);
        if(index==null){
            index=1;
        }
        Pagers<SAPDto> pagers = service.findByPagers(sap, index, 5);
        //可以查询到店铺
        if(pagers.getList()!=null && pagers.getList().size()>0){
            model.addAttribute("msg","查询成功");
            model.addAttribute("p",pagers);
        }else {
            model.addAttribute("msg","未找到该店铺");
        }
        return "forward:/WEB-INF/pages/details_list.jsp";
    }

}
