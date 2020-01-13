package com.fh.service.impl;

import com.fh.dao.StuDao;
import com.fh.model.PageBean;
import com.fh.model.Stu;
import com.fh.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuDao stuDao;
    @Override
    public PageBean<Stu> queryStuList(PageBean<Stu> pageBean) {
        Long total=stuDao.queryStuCount();
        pageBean.setRecordsFiltered(total);
        pageBean.setRecordsTotal(total);
        List<Stu> list=stuDao.queryStuList(pageBean);
        pageBean.setData(list);
        return pageBean;
    }

    @Override
    public void addStu(Stu stu) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        stu.setIp(localHost.toString());
        stu.setIsDel(1);
        stuDao.insert(stu);
    }

    @Override
    public Stu queryStuById(Integer id) {
        return stuDao.selectById(id);
    }

    @Override
    public void saveStu(Stu stu) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        stu.setIp(localHost.toString());
        stuDao.updateById(stu);
    }

    @Override
    public void deleteStu(Integer id) {
        stuDao.updateIsDel(id);
    }
}
