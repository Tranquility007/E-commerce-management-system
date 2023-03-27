package com.zd.controller;

import com.zd.domain.Products;
import com.zd.utils.Pagers;
import com.zd.domain.Shops;
import com.zd.service.IShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shops")
public class ShopsController {
    @Autowired
    private IShopsService service;

    /**
     * 进入shops_add.jsp界面
     * @return
     */
    @RequestMapping("tosave")
    public String toAdd() {
        return "shops_add";
    }

    /**
     * 保存店铺
     * @param shops
     * @param model
     * @return
     */
    @RequestMapping("save")
    public String add(Shops shops, Model model) {
        //增加一个店铺
        int insert = 0;
        if (null != shops) {
            insert = service.add(shops);
        }
        if (insert > 0) {
            //增加成功
            model.addAttribute("msg", "增加成功");
            return "forward:/shops/findByPagers";
        } else {
            model.addAttribute("msg", "增加失败");
            return "shops_add";
        }
    }

    /**
     * 渲染店铺信息修改界面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toupdate/{id}")
    public String toUpdate(@PathVariable("id") String id, Model model) {
        System.out.println(id);
        Shops shop = null;
        if (null != id && !id.equals("")) {
            shop = service.findById(id);
        }
        if (null != shop) {
            //查询到了改店铺
            //将该对象存在域中
            model.addAttribute("shop", shop);
            //请求转发到shops_update.jsp
            return "forward:/WEB-INF/pages/shops_update.jsp";
        } else {
            model.addAttribute("msg", "修改失败");
            return "shops_list";
        }
    }

    /**
     * 修改店铺信息
     * @param shop
     * @param model
     * @return
     */
    @RequestMapping("/update")
    public String update(Shops shop, Model model) {
        //获取界面数据
        int update = 0;
        if (null != shop) {
            //调用修改方法，修改信息
            update = service.update(shop);
        }
        if (update > 0) {
            //修改成功
            model.addAttribute("msg", "修改成功");
            return "forward:/shops/findByPagers";
        } else {
            model.addAttribute("msg", "修改失败");
            return "shops_list";
        }
    }

    /**
     * 分页查询
     * @param index
     * @param model
     * @return
     */
    @RequestMapping("/findByPagers")
    public String findByPagers(Integer index, Model model){
        if(index==null){
            index=1;
        }
        Pagers<Shops> pagers = service.findByName(index, 3);
        model.addAttribute("p",pagers);
        return "shops_list";
    }

    /**
     * 根据id删除店铺
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        int i = 0;
        if (id != null && !id.equals("")) {
            //调用service的方法，删除该id
            i = service.delete(id);
        }
        if (i > 0) {
            //删除成功
            model.addAttribute("msg", "删除成功");
            return "forward:/shops/findByPagers";
        } else {
            model.addAttribute("msg", "删除失败");
            return "shops_list";
        }
    }

    /**
     * 根据名称模糊查询
     * @param name
     * @param index
     * @param model
     * @return
     */
    @RequestMapping("/findByName")
    public String findByName(@RequestParam("title")String name, Integer index, Model model){
        //接收名字
        System.out.println(name);
        // 包含分页信息的列表
        Pagers<Shops> pagers = null;
        if(index==null){
            index=1;
        }
        if(null!=name && !name.equals("")){
            pagers = service.findByName(name, index, 3);
            System.out.println(pagers);
        }
        if(null!=pagers && pagers.getList().size()>0){
            //查询成功
            model.addAttribute("msg","查询成功");
            model.addAttribute("p",pagers);
            return "shops_list";
        }else{
            model.addAttribute("msg","不存在该条记录");
            return "forward:/shops/findByPagers";
        }

    }

    /**
     * 查询店铺id
     * @param id
     * @return
     */
    @RequestMapping("/selectId")
    @ResponseBody
    public String selectId(@RequestParam("id") String id){
        System.out.println("ajax请求获取到的店铺ID;"+id);
        //根据id查询店铺信息
        Shops shop =  service.findById(id);
        System.out.println(shop);
        if(shop!=null){
            //查到了数据
            return "yes";
        }else {
            return "no";
        }
    }

    /**
     * 查询店铺名称
     * @param title
     * @return
     */
    @RequestMapping("/selectTitle")
    @ResponseBody
    public String selectTitle(@RequestParam("title") String title){
        System.out.println("ajax请求获取到的店铺名称："+title);
        //根据id查询店铺信息
        Shops shop =  service.findTitle(title);
        System.out.println(shop);
        if(shop!=null){
            //查到了数据
            return "yes";
        }else {
            return "no";
        }
    }

}
