package com.fh.controller;


import com.fh.model.PageBean;
import com.fh.model.Stu;
import com.fh.service.StuService;
import com.fh.util.OSSClientUtil;
import com.fh.util.ResponseServer;
import com.fh.util.ServerEnum;
import com.fh.util.excelUtils.ExportExcelUtils;

import com.fh.util.login.LoginAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("stu")
@CrossOrigin(maxAge = 3600,exposedHeaders = "NOLOGIN")
public class StuController {
    private static final Logger logger = LoggerFactory.getLogger(StuController.class);
    @Autowired
    private StuService stuService;
    @GetMapping
    @LoginAnnotation
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
    @GetMapping("exportExcel")
    public ResponseServer exportExcel() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Stu> list= stuService.queryExcelList();
       /* Class<Stu> stuClass = Stu.class;
        Field[] fields = stuClass.getDeclaredFields();
        Map<Integer,Field> fieldMap=new HashMap<>();
        Stu s =new Stu();
        for (int i=0;i<fields.length;i++){
            if(fields[i].isAnnotationPresent(ExportField.class)){
                ExportField attr=fields[i].getAnnotation(ExportField.class);
                fields[i].setAccessible(true);

            }

        }*/
        if(list.size()==0){
            return ResponseServer.error(ServerEnum.EXPORT_NULL);
        }
        //导出
        String url=ExportExcelUtils.export(list,Stu.class);
        return ResponseServer.success(url);
    }

}
