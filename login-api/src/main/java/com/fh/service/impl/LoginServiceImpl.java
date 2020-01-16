package com.fh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.dao.LoginDao;


import com.fh.model.VipPo;
import com.fh.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public VipPo isRegister(String phone) {
        QueryWrapper<VipPo> queryWrapper=new QueryWrapper<VipPo>();
        queryWrapper.eq("phone",phone);
        VipPo vipPo = loginDao.selectOne(queryWrapper);
        if(vipPo == null){
            vipPo=new VipPo();
            vipPo.setPhone(phone);
            vipPo.setCreateDate(new Date());
            loginDao.insert(vipPo);

        }else{
            loginDao.updateById(vipPo);
        }
        return vipPo;
    }



}
