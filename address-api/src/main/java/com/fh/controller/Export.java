package com.fh.controller;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fh.model.Address;
import com.fh.service.AddrService;

import com.fh.util.wyx_export;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.List;

@Controller

public class Export {
    @Autowired
    private AddrService addrService;

    @RequestMapping("/export")
    public void Export(  Integer id, HttpServletResponse response){
        List<Address> stus = addrService.queryExcelList(id);
        List<Address> addresses=addrService.queryExcelListByPid(id);
        stus.addAll(addresses);
        wyx_export.export(stus,response);
    }

    public static void main (String [] a){
     /*   Class<Address> addressClass = Address.class;
        TableName annotation = addressClass.getAnnotation(TableName.class);
        Field[] declaredFields = addressClass.getDeclaredFields();
        String sql="select ";
        String coulmn= "";
        for (int i=0;i<declaredFields.length;i++){
            TableField annotation1 = declaredFields[i].getAnnotation(TableField.class);
            if (annotation1 !=null){
                String value = annotation1.value();
                coulmn+=value+",";
            }
        }
         coulmn =coulmn.substring(0,coulmn.length()-1);
        sql +=coulmn;
        sql+=" from ";
        if (annotation != null){
            String value = annotation.value();
            sql+=value;
        }
        System.out.println(sql);
        */


    }

}
