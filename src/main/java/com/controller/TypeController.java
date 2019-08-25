package com.controller;

import com.entity.Type;
import com.github.pagehelper.PageInfo;
import com.service.TypeService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")   //表示后所有的控制器请求都在/admin目录下
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/getType")
    @ResponseBody
    public Map<String,Object> getType(Page page){
        PageInfo<Type> pageInfo=typeService.getAllType(page);
        Map<String, Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addType")
    @ResponseBody
    public String addType(Type type){
        int temp=-1;
        try {
             temp=typeService.addType(type);
        }catch (Exception e){
            e.printStackTrace();
        }return "{\"result\":"+temp+"}";
}
    @RequestMapping("/getOneType")
    @ResponseBody
    public Type getType(Integer id){
        return typeService.getType(id);
    }
    @RequestMapping("/updateType")
    @ResponseBody
    public String getType(Type type){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=typeService.updateType(type);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/delType")
    @ResponseBody
    public String delType(Integer id){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=typeService.delType(id);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/delMoreType")
    @ResponseBody
    public String delType(String ids){
        String arys[] = ids.split(",");
        Integer [] is= new Integer[arys.length];
        for (int i = 0 ; i < arys.length;i++){
            is[i]=new Integer(arys[i]);
        }
        int tmep=this.typeService.delMoreType(is);
        return "{\"result\":"+tmep+"}";
    }


}

