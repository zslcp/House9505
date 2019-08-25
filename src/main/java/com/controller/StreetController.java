package com.controller;


import com.entity.Street;
import com.github.pagehelper.PageInfo;
import com.service.StreetService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class StreetController {

    @Autowired
    private StreetService streetService;

    @RequestMapping("/getStreetByDid")
    @ResponseBody
    public Map<String,Object> getStreetByDid(Integer did, Page page) {
         PageInfo<Street> pageInfo=streetService.getStreetByDistrict(did,page);
         //返回结果
        HashMap<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("/getStreet")   //dagagrid会自动传page页码,rows页大小
    @ResponseBody
    public Map<String,Object> getStreet(Page page){
        PageInfo<Street> pageInfo= streetService.getAllStreet(page);

        //返回total键，rows的页数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("/addStreet")   //dagagrid会自动传page页码,rows页大小
    @ResponseBody
    public String addStreet(Street street){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=streetService.addStreet(street);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }


    //通过主键查询单 条
    @RequestMapping("/getOneStreet")
    @ResponseBody
    public Street getStreet(Integer id){
        return streetService.getStreet(id);
    }

    //修改区域
    @RequestMapping("/updateStreet")
    @ResponseBody
    public String getStreet(Street street){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=streetService.updateStreet(street);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }

    //修改区域
    @RequestMapping("/delStreet")
    @ResponseBody
    public String delStreet(Integer id){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=streetService.delStreet(id);
            System.out.println("ttttt"+temp);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        System.out.println("ttttt"+temp);
        return "{\"result\":"+temp+"}";
    }

    //批量删除区域
    //delMoreDistrict?id=1&id=2&id=3  = public String delDistrict(Integer []id)
    @RequestMapping("/delMoreStreet")  //1,2,3
    @ResponseBody
    public String delStreet(String ids){
        //将字符串转化为数组
        String arys[]=ids.split(",");
        Integer [] is=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            is[i]=new Integer(arys[i]);
        }
        //调用业务
        int temp=this.streetService.delMoreStreet(is);
        return "{\"result\":"+temp+"}";
    }



}
