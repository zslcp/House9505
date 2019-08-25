
package com.controller;

import com.entity.District;
import com.github.pagehelper.PageInfo;
import com.service.DistrictService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/admin/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("/getDistrict")   //dagagrid会自动传page页码,rows页大小
    @ResponseBody
    public Map<String,Object> getDistrict(Page page){
        PageInfo<District> pageInfo= districtService.getAllDistrict(page);
        //返回total键，rows的页数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }


    @RequestMapping("/addDistrict")   //dagagrid会自动传page页码,rows页大小
    @ResponseBody
    public String addDistrict(District district){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=districtService.addDistrict(district);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    @RequestMapping("/updateDistrict")   //dagagrid会自动传page页码,rows页大小
    @ResponseBody
    public String updateDistrict(District district){
        int temp=-1;
        try{
            //调用业务实现添加
            temp=districtService.updateDistrict(district);
            //传统实现:跳转到视图
            //返回数据
        }catch (Exception ex){
            ex.printStackTrace(); //都会选择记录日志
        }
        return "{\"result\":"+temp+"}";
    }
    //通过主键查询单 条
    @RequestMapping("/getOneDistrict")
    @ResponseBody
    public District getDistrict(Integer id){
        return districtService.getDistrict(id);
    }


    @RequestMapping("/delDistrict")
    @ResponseBody
    public String delDistrict(Integer id){
        int temp=-1;
        try{
            temp=districtService.delDistrict(id);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "{\"result\":"+temp+"}";
    }
    //批量删除区域
    //delMoreDistrict?id=1&id=2&id=3  = public String delDistrict(Integer []id)
    @RequestMapping("/delMoreDistrict")  //1,2,3
    @ResponseBody
    public String delDistrict(String ids){
        //将字符串转化为数组
        String arys[]=ids.split(",");
        Integer [] is=new Integer[arys.length];
        for (int i=0;i<arys.length;i++){
            is[i]=new Integer(arys[i]);
        }
        //调用业务
        int temp=this.districtService.delMoreDistrict(is);
        return "{\"result\":"+temp+"}";
    }




}

