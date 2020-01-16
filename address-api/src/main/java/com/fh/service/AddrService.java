package com.fh.service;

import com.fh.model.Address;

import java.util.List;
import java.util.Map;

public interface AddrService {
    List<Map<String,Object>> queryAddrTree();

    void addAddr(Address address);

    Address queryAddrById(Integer id);

    Address queryPaddrById(Integer pid);

    void updateAddrById(Address address);

    void deleteAddr(String[] addrIds);

    List<Address> queryExcelList(Integer id);

    List<Address> queryExcelListByPid(Integer id);
}
