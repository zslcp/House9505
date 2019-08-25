package com.service.impl;
import com.entity.District;
import com.entity.DistrictExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.DistrictMapper;
import com.service.DistrictService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;
      //分页查询
    @Override
    public PageInfo<District> getAllDistrict(Page page) {
        //1.开启分页
        PageHelper.startPage(page.getPage(),page.getRows());
        //2.查询所有
        //封装条件实体类
        DistrictExample districtExample=new DistrictExample();
        List<District> list=districtMapper.selectByExample(districtExample);
        //3.获取分页相关的属性
        PageInfo<District> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
   //新增
    @Override
    public int addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }


    //修改
    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }
    //修改回显查单条
    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delDistrict(Integer id) {
        return districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreDistrict(Integer[] ids) {
    /*    DistrictExample e=new  DistrictExample ();
        DistrictExample .Criteria c=e.createCriteria();
        c.andIdIn(Arrays.asList(is));
        return districtMapper.deleteByExample(e);*/
        return districtMapper.delMoreDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }


}

