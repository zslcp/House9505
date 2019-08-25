package com.service.impl;
import com.entity.District;
import com.entity.DistrictExample;
import com.entity.Street;
import com.entity.StreetExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.StreetMapper;
import com.service.StreetService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<Street> getStreetByDistrict(Integer did, Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        //创建查询条件
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did); //封装查询
        List<Street> list=  streetMapper.selectByExample(streetExample);
        PageInfo<Street> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Street> getStreetByDistrict(Integer did) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria=streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did); //封装查询
        List<Street> list=  streetMapper.selectByExample(streetExample);
        return list;
    }

    @Override
    public PageInfo<Street> getAllStreet(Page page) {
        //1.开启分页
        PageHelper.startPage(page.getPage(),page.getRows());
        //2.查询所有
        //封装条件实体类
        StreetExample streetExample=new StreetExample();
        List<Street> list=streetMapper.selectByExample(streetExample);
        //3.获取分页相关的属性
        PageInfo<Street> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int addStreet(Street street) {
        return streetMapper.insertSelective(street);
    }

    @Override
    public Street getStreet(Integer id) {
        return streetMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStreet(Street street) {
        return streetMapper.updateByPrimaryKeySelective(street);
    }


    @Override
    public int delStreet(Integer id) {
       return streetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreStreet(Integer[] ids) {
        return streetMapper.delMoreStreet(ids);
    }

}
