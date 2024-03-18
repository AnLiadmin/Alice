package com.qf.service.impl;

import com.qf.dao.AddrDao;
import com.qf.dao.impl.AddrDaoImpl;
import com.qf.entity.Address;
import com.qf.service.AddrService;

import java.util.List;

public class AddrServiceImpl implements AddrService {
    private AddrDao addrDao = new AddrDaoImpl();
    @Override
    public List<Address> selectByUid(int u_id) {
        return addrDao.selectByUid(u_id);
    }

    @Override
    public int addAddress(Address addr) {
        return addrDao.addAddress(addr);
    }

    @Override
    public int deleteByAid(String aid) {
        return addrDao.deleteByAid(aid);
    }

    @Override
    public int updateAddr(Address addr) {
        return addrDao.updateAddr(addr);
    }

    @Override
    public int updateDefault(String aid, int u_id) {
        //根据uid设置为非默认
        int res = addrDao.setNoDefault(u_id);
        System.out.println("设置非默认:"+res);
        //根据aid设置为默认
        res = addrDao.setDefault(aid);
        System.out.println("设置默认:"+res);
        return 0;
    }
}
