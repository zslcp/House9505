package com.potal.controller;

import com.entity.Type;
import com.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "typeController1")
@RequestMapping("/page")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping("/getAllType")
    @ResponseBody
    public List<Type> getAllType(){
        return typeService.getAllType();
    }
}
