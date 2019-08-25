package com.service;

import com.entity.House;
import com.github.pagehelper.PageInfo;
import com.util.HouseCondition;
import com.util.Page;

public interface HouseService {

   //新增出租房
    public int addHouse(House house);

   //查询用户发布的出租房
    public PageInfo<House> getHouseByUser(Page page,Integer userid);

    //删除出租房
    public int delHouseState(String id,Integer state);

    //根据ID查查单条
     House getHouse(String id);

     //修改出租房
    public int updateHouse(House house);

    /**
     * 通过审核状态查询出租房信息
     *  状态传1 表示已审核
     * 状态传0 表示未审核
     * @return 影响行数
     */
    public PageInfo<House> getHouseByIsPass(Page page,Integer state);

    //修改出租房的审核状态
    public int PassHouse(String id,Integer state);




    //浏览出租房信息，查询条件， HOUSECondititon

 public PageInfo<House> getHouseBySearch(HouseCondition condition);





}
