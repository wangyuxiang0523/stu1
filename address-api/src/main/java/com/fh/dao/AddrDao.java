package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddrDao extends BaseMapper<Address> {
    List<Address> selectListById(Integer id);

    List<Address> selectListByPid(Integer id);
}
