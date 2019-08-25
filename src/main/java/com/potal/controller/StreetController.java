package com.potal.controller;


import com.entity.Street;
import com.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller(value = "streetController2")
@RequestMapping("/page")


public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("/getStreetByDid")
    @ResponseBody
    public List<Street> getStreetByDid(Integer did) {
       return streetService.getStreetByDistrict(did);
    }
}
