package com.service;
import com.entity.Type;
import com.github.pagehelper.PageInfo;
import com.util.Page;
import java.util.List;

public interface TypeService {
    /**
     */
    PageInfo<Type> getAllType(Page page);

    //添加业务

    public int addType(Type Type);

    /**
     * 查询条信息
     */
    public Type getType(Integer id);

    /**
     * 修改区域
     */
    public int updateType(Type Type);

    /**
     *  删除区域
     */
    public int delType(Integer id);


    /**
     *  批量删除区域
     */
    public int delMoreType(Integer[] ids);


    /**
     * 查询所有类型
     */
    public List<Type> getAllType();

}
