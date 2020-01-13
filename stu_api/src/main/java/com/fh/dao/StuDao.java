package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.PageBean;
import com.fh.model.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StuDao extends BaseMapper<Stu> {
    Long queryStuCount();

    List<Stu> queryStuList(PageBean<Stu> pageBean);

    void updateIsDel(Integer id);
}
