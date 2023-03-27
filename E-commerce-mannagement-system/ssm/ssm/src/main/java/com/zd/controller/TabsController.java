package com.zd.controller;

import com.zd.dto.TAPDto;
import com.zd.utils.Pagers;
import com.zd.domain.Tabs;
import com.zd.service.ITabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tabs")
public class TabsController {
    @Autowired
    private ITabsService service;

    /**
     * 商品类别对应的商品数量
     *
     * @param index
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String toTabs(Integer index, Model model) {
        //查询商品类别对应的商品数量
        if (index == null) {
            index = 1;
        }
        Pagers<TAPDto> pagers = service.findCount(index, 3);
        model.addAttribute("p", pagers);
        return "tabs_count";
    }

    /**
     * 对修改界面进行数据填充
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toupdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        //调用service的findById方法，查询对应的Tabs对象
        Tabs tab = null;
        if (null != id && !id.equals("")) {
            tab = service.findById(id);
        }
        if (null != tab) {
            //将该对象存在域中
            model.addAttribute("tab", tab);
            //请求转发到tabs_update.jsp
            return "forward:/WEB-INF/pages/tabs_update.jsp";
        } else {
            model.addAttribute("msg", "修改失败");
            return "tabs_list";
        }

    }

    @RequestMapping("/update")
    public String update(Tabs tab, Model model) {
        //获取界面数据
        int update = 0;
        if (null != tab) {
            //调用修改方法，修改信息
            update = service.update(tab);
        }
        if (update > 0) {
            //修改成功
            model.addAttribute("msg", "修改成功");
            return "forward:/tabs/findByPagers";
        } else {
            model.addAttribute("msg", "修改失败");
            return "tabs_list";
        }
    }

    /**
     * 进入商品类别添加界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/tosave")
    public String toSave(Model model) {
        //更新商品类别
        return "tabs_add";
    }

    /**
     * 增加商品类型
     *
     * @param tab
     * @param model
     * @return
     */
    @RequestMapping("/save")
    public String add(Tabs tab, Model model) {
        //增加一个商品类型
        int insert = 0;
        if (null != tab) {
            insert = service.add(tab);
        }
        if (insert > 0) {
            //增加成功
            model.addAttribute("msg", "增加成功");
            return "forward:/tabs/findByPagers";
        } else {
            model.addAttribute("msg", "增加失败");
            return "tabs_add";
        }
    }

    /**
     * 分页查询
     *
     * @param index
     * @param model
     * @return
     */
    @RequestMapping("/findByPagers")
    public String findAll(Integer index, Model model) {
        if (index == null) {
            index = 1;
        }
        Pagers<Tabs> pagers = service.findByPagers(index, 3);
        model.addAttribute("p", pagers);
        return "tabs_list";
    }

    /**
     * 根据id删除
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        //获取id
        System.out.println(id);
        int i = 0;
        if (id != null && !id.equals("")) {
            //调用service的方法，删除该id
            i = service.delete(id);
        }
        if (i > 0) {
            //删除成功
            model.addAttribute("msg", "删除成功");
            return "forward:/tabs/findByPagers";
        } else {
            model.addAttribute("msg", "删除失败");
            return "tabs_list";
        }
    }

    /**
     * 根据名称模糊分页查询
     *
     * @param name
     * @param index
     * @param model
     * @return
     */
    @RequestMapping("/findByName")
    public String findByName(@RequestParam("name") String name, Integer index, Model model) {
        //接收名字
//        System.out.println(name);
        // 包含分页信息的列表
        Pagers<Tabs> pagers = null;
        if (index == null) {
            index = 1;
        }
        if (null != name && !name.equals("")) {
            pagers = service.findByName(name, index, 3);
//            System.out.println(pagers);
        }
        if (null != pagers && !pagers.equals("")) {
            //查询出了商品类别
            //查询成功
            model.addAttribute("msg", "查询成功");
            model.addAttribute("p", pagers);
            return "tabs_list";
        } else {
            model.addAttribute("msg", "不存在该条记录");
            return "forward:/tabs/findByPagers";
        }
    }

    /**
     * 查询商品类别id
     * @param tid
     * @return
     */
    @RequestMapping("/selectTid")
    @ResponseBody
    public String selectTid(@RequestParam("tid") Integer tid) {
        System.out.println("获取到的商品类别id：" + tid);
        Tabs tab = service.findById(tid);
        if (tab != null) {
            //查询到了数据
            return "yes";
        } else {
            return "no";
        }
    }

    /**
     * 查询商品类别名称
     * @param name
     * @return
     */
    @RequestMapping("/selectName")
    @ResponseBody
    public String selectName(@RequestParam("name") String name) {
        System.out.println("获取到的商品类别名称：" + name);
        Tabs tab = service.findName(name);
        if (tab != null) {
            //查询到了数据
            return "yes";
        } else {
            return "no";
        }
    }
}
