package com.fh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fh.annotation.ExcelAnnotation;
import com.fh.annotation.Export;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@TableName("w_stu")
@ExcelAnnotation(title = "用户信息",sheetName = "用户信息",mkdir = "user")
@Export(title = "Stu")
public class Stu {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @Export(name = "名字")
    @ExcelAnnotation(columnName = "名字")
    private String name;
    @Export(name = "年龄")
    @ExcelAnnotation(columnName = "年龄")
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    @Export(name = "ip")
    @ExcelAnnotation(columnName = "ip")
    private String ip;
    @TableField(value = "isDel")
    private Integer isDel;
    @TableField(value = "imgPath")
    private String imgPath;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
