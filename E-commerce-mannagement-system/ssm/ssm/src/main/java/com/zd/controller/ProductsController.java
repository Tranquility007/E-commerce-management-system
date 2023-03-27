package com.zd.controller;

import com.zd.utils.Pagers;
import com.zd.domain.Products;
import com.zd.domain.Tabs;
import com.zd.dto.ProductsDto;
import com.zd.service.IProductsService;
import com.zd.service.ITabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private IProductsService service;
    @Autowired
    private ITabsService tabsService;

    /**
     * 查询所有商品信息，包括商品对应的种类名称
     * @param model
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(Model model){
//        初始化商品列表
        List<Tabs> tabs = tabsService.findAll();
        model.addAttribute("tabs", tabs);
        List<ProductsDto> products = service.findAll();
        model.addAttribute("products",products);
        return "products_list";
    }

    @RequestMapping("/findByPagers")
    public String findByPagers(Integer index,ProductsDto products,Model model){
        //初始化商品列表
        List<Tabs> tabs = tabsService.findAll();
        model.addAttribute("tabs", tabs);
        if(index==null){
            index=1;
        }
        Pagers<ProductsDto> pagers = service.findByPagers(products, index, 3);
        model.addAttribute("p",pagers);
        return "products_list";
    }

    @RequestMapping("/findByName")
    public String findByName(@RequestParam("title")String name,Integer index,Model model){
        //初始化商品列表
        List<Tabs> tabs = tabsService.findAll();
        model.addAttribute("tabs", tabs);
        //接收名字
        System.out.println(name);
        // 包含分页信息的列表
        Pagers<ProductsDto> pagers = null;
        if(index==null){
            index=1;
        }
        if(null!=name && !name.equals("")){
            pagers = service.findByName(name, index, 3);
        }
        if(null!=pagers && pagers.getList().size()>0){
            model.addAttribute("p",pagers);
            return "products_list";
        }else{
            return "forward:/products/findByPagers";
        }

    }


    /**
     * products_add.jsp保存在WEB-INF界面不能直接访问
     * 进入保存商品界面（需要更新界面的商品类别列表）
     * @param model
     * @return
     */
    @RequestMapping("/tosave")
    public String toSave(Model model){
        //更新商品类别
       // 初始化商品列表
        List<Tabs> tabs = tabsService.findAll();
        model.addAttribute("tabs", tabs);
        return "products_add";
    }

    /**
     * 根据传递过来的商品信息保存商品，jsp界面的name值与product里的属性值对应
     * @param product
     * @return
     */
    @RequestMapping("/save")
    public String save(Products product, Model model){
        //根据传递过来的product信息，保存商品信息
        int i = service.save(product);
        if (i > 0) {
            //增加成功
            model.addAttribute("msg", "增加成功");
            return "forward:/products/findByPagers";
        } else {
            model.addAttribute("msg", "增加失败");
            return "products_list";
        }
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String productId,Model model){
        //根据id删除商品
        int i = service.deleteById(productId);
        if(i>0){
            model.addAttribute("msg","删除成功");
            return "forward:/products/findByPagers";
        }else {
            return "products_list";
        }
    }
    /**
     * 根据ID查询商品所有信息，并将其填充到对应的jsp界面
     * @param productId
     * @return
     */
    @RequestMapping("/toupdate/{id}")
    public String toUpdate(@PathVariable("id") String productId,Model model){
        //初始化下拉列表
        List<Tabs> tabs = tabsService.findAll();
        model.addAttribute("tabs", tabs);
        //根据id查询商品信息
       Products product =  service.findById(productId);
       model.addAttribute("product",product);
       return "products_update";
    }

    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @RequestMapping("/update")
    public String update(Products product, Model model){
        int i = service.update(product);
        if(i>0){
            model.addAttribute("msg","修改成功");
            return "forward:/products/findByPagers";
        }else {
            model.addAttribute("msg","修改失败");
            return "products_list";
        }

    }

    /**
     * 根据商品类别的id查询商品信息
     * @param id 类别id
     * @param model 视图
     * @param index 当前页数
     * @return
     */
    @RequestMapping("/findByTabId/{id}")
    public String findByTabId(@PathVariable("id") String id,Model model,Integer index){
//        System.out.println(id);
         List<Tabs> tabs = tabsService.findAll();
         model.addAttribute("tabs", tabs);
         model.addAttribute("id",id);
        if(index==null){
            index=1;
        }
        Pagers<ProductsDto> pagers = service.findByTabId(id,index,3);
        if(null != pagers && pagers.getList().size()>0){
            model.addAttribute("p",pagers);
            return "products_list";
        }else {
            return "forward:/products/findByPagers";
        }
    }

    /**
     * 查询商品id
     * @param proid
     * @return
     */
    @RequestMapping("/selectProid")
    @ResponseBody
    public String findProId(@RequestParam("proid") String proid){
        System.out.println("ajax请求获取到的商品ID;"+proid);
        //根据id查询商品信息
        Products product =  service.findById(proid);
        System.out.println(product);
        if(product!=null){
            //查到了数据
            return "yes";
        }else {
            return "no";
        }
    }

    /**
     * 查询淘宝id
     * @param comid
     * @return
     */
    @RequestMapping("/selectcid")
    @ResponseBody
    public String findCid(@RequestParam("comid") String comid){
        System.out.println("ajax请求获取到的淘宝ID;"+comid);
        //根据id查询商品信息
        Products product =  service.findByCid(comid);
        System.out.println(product);
        if(product!=null){
            //查到了数据
            return "yes";
        }else {
            return "no";
        }
    }
}

