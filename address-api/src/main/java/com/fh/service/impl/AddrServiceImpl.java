package com.fh.service.impl;

import com.fh.dao.AddrDao;
import com.fh.model.Address;
import com.fh.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddrServiceImpl implements AddrService {
    @Autowired
    private AddrDao addrDao;
    @Override
    public List<Map<String, Object>> queryAddrTree() {
        List<Address> addresses = addrDao.selectList(null);
        return getAddrTree(0,addresses);
    }

    @Override
    public void addAddr(Address address) {
        addrDao.insert(address);
    }

    @Override
    public Address queryAddrById(Integer id) {
        return addrDao.selectById(id);
    }

    @Override
    public Address queryPaddrById(Integer pid) {
        return addrDao.selectById(pid);
    }

    @Override
    public void updateAddrById(Address address) {
        addrDao.updateById(address);
    }

    @Override
    public void deleteAddr(String[] addrIds) {
       addrDao.deleteBatchIds(Arrays.asList(addrIds));
    }

    @Override
    public List<Address> queryExcelList(Integer id) {
        return addrDao.selectListById(id);
    }

    @Override
    public List<Address> queryExcelListByPid(Integer id) {
        return addrDao.selectListByPid(id);
    }

    public List<Map<String,Object>> getAddrTree(Integer pid,List<Address> addresses){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        addresses.forEach(list1->{
            Map<String,Object>map=null;
            if(pid.equals(list1.getPid())){
                map=new HashMap<String,Object>();
                map.put("id",list1.getId());
                map.put("name",list1.getName());
                map.put("children",getAddrTree(list1.getId(),addresses));
            }
            if(map!=null){
                list.add(map);
            }
        });
        return list;
    }
}
