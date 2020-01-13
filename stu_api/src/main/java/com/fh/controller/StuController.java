package com.fh.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.fh.model.PageBean;
import com.fh.model.Stu;
import com.fh.service.StuService;
import com.fh.util.OSSClientUtil;
import com.fh.util.ResponseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("stu")
@CrossOrigin(maxAge = 3600)
public class StuController {
    @Autowired
    private StuService stuService;
    @GetMapping
    public PageBean<Stu> queryStuList(PageBean<Stu> pageBean){
        pageBean=  stuService.queryStuList(pageBean);
        return pageBean;
    }

    @PutMapping
    public ResponseServer addStu(Stu stu) throws UnknownHostException {
        stuService.addStu(stu);
        return ResponseServer.success();
    }
    @RequestMapping("uploadFile")
    public Map<String, Object> uploadFile(MultipartFile photo) {
        Map<String,Object> map= new HashMap<>();
        OSSClientUtil ossClientUtil=new OSSClientUtil();
        try {
            String url = ossClientUtil.uploadImg2Oss(photo);
            map.put("data",url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    @GetMapping("getStuById")
    public Map<String,Object> queryDrugById(Integer id){
        Map<String,Object>map=new HashMap<>();
//

        Stu stu=stuService.queryStuById(id);

        map.put("data",stu);
        return map;
    }
    @PostMapping("saveStu")
    public ResponseServer saveStu(Stu stu) throws UnknownHostException {
        stuService.saveStu(stu);
        return ResponseServer.success();
    }
    @DeleteMapping
    public ResponseServer deleteStu(Integer id){
        stuService.deleteStu(id);
        return ResponseServer.success();
    }
}
