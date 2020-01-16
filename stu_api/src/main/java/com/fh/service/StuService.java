package com.fh.service;

import com.fh.model.PageBean;
import com.fh.model.Stu;

import java.net.UnknownHostException;
import java.util.List;

public interface StuService {
    PageBean<Stu> queryStuList(PageBean<Stu> pageBean);

    void addStu(Stu stu) throws UnknownHostException;

    Stu queryStuById(Integer id);

    void saveStu(Stu stu) throws UnknownHostException;

    void deleteStu(Integer id);

    List<Stu> queryExcelList();
}
