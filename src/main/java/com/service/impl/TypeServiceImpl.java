package com.service.impl;

import com.entity.Type;
import com.entity.TypeExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.StreetMapper;
import com.mapper.TypeMapper;
import com.service.TypeService;
import com.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private StreetMapper streetMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PageInfo< Type> getAllType(Page page) {
        PageHelper.startPage(page.getPage(),page.getRows());
        TypeExample typeExample = new TypeExample();
        List<Type> list = typeMapper.selectByExample(typeExample);
        PageInfo<Type> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int addType(Type type) {
        return typeMapper.insertSelective(type );
    }

    @Override
    public Type getType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public int delType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delMoreType(Integer[] ids) {
        return typeMapper.delMoreType(ids);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }

}
