package com.fh.controller;

import com.fh.model.Address;
import com.fh.service.AddrService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("addr")
@CrossOrigin(maxAge = 3600)
public class AddrController {
    @Autowired
    private AddrService addrService;
    @PostMapping("queryAddrZtree")
    public List<Map<String,Object>> queryAddrTree(){
        return addrService.queryAddrTree();
    }
    @PostMapping("addAddr")
    public Map addAddr(Address address){
        Map<String,Object> map =new HashMap<>();

        if(address.getId()!=null){

            addrService.updateAddrById(address);
            map.put("code",200);
            map.put("addr",address);
        }else {
            addrService.addAddr(address);
            map.put("code",300);
            map.put("addr",address);
        }

        return map;
    }
    @PostMapping("toUpdateAddr")
    public Map<String, Object> toUpdateAddr(Integer id){
        Address address= addrService.queryAddrById(id);
        Address address1 =addrService.queryPaddrById(address.getPid());
        Map<String,Object> map =new HashMap<>();
        map.put("addr",address);
        map.put("paddr",address1);
        return map;
    }
    @PostMapping("deleteAddrByIds")
    public void deleteAddr(@RequestParam String []addrIds ){

        addrService.deleteAddr(addrIds);
    }
}
