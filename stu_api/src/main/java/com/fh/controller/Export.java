package com.fh.controller;

import com.fh.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class Export {
    @Autowired
    private StuService stuService;

    @RequestMapping("/export")
    public void Export(){
        stuService.queryExcelList();
    }
}
