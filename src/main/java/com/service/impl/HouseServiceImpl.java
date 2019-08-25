package com.service.impl;

import com.entity.House;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.HouseMapper;
import com.service.HouseService;
import com.util.HouseCondition;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUser(Page page, Integer userid) {
        //给page的rows属性设置默认值:后期可能不传页大小
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> list=houseMapper.getHouseByUser(userid);
        return new PageInfo<House>(list);
    }

    @Override
    public int delHouseState(String id, Integer state) {
        House house=new House();
        //设置id
        house.setId(id);
        house.setIsdel(state);
        return houseMapper.updateByPrimaryKeySelective(house);
    }
    @Override
    public House getHouse(String id) {
        return houseMapper.getHouseById(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }


    @Override
    public PageInfo<House> getHouseByIsPass(Page page, Integer state) {
        //开启分页
        PageHelper.startPage(page.getPage(),page.getRows());
        //查询所有
        List<House> list=houseMapper.getHouseByIsPass(state);
        return new PageInfo<House>(list);
    }

    @Override
    public int PassHouse(String id, Integer state) {
        House house=new House();
        //设置id
        house.setId(id);
        //设置审核状态
        house.setIspass(state);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getHouseBySearch(HouseCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> list=houseMapper.getHouseBySearch(condition);
        return new PageInfo<>(list);
    }

}

