
package com.service;

import com.entity.District;
import com.github.pagehelper.PageInfo;
import com.util.Page;

import java.util.List;


public interface DistrictService {

    PageInfo<District> getAllDistrict(Page page);

    int addDistrict(District district);

    int updateDistrict(District district);

    public District getDistrict(Integer id);

    //删除区域
    public int delDistrict(Integer id);

    int delMoreDistrict(Integer[] ids);

    //查询所有
    List<District> getAllDistrict();
}

