package com.mapper;

import com.entity.House;
import com.entity.HouseExample;
import java.util.List;

import com.util.HouseCondition;
import org.apache.ibatis.annotations.Param;

public interface HouseMapper {

    int countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //查询用户发布的出租房
    public List<House> getHouseByUser(Integer userid);

    //查询出租房信息，
    public House getHouseById(String id);


    /**
     * 查询出租房的状态信息
     * 审核状态为0 表示未审核
     * 审核状态为1 表示已审核
     * @return
     */
    public List<House> getHouseByIsPass(Integer ispass);

    public List<House> getHouseBySearch(HouseCondition condition);

}