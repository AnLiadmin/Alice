package com.qf.service;

import com.qf.entity.Address;

import java.util.List;

public interface AddrService {
    List<Address> selectByUid(int u_id);

    int addAddress(Address addr);

    int deleteByAid(String aid);

    int updateAddr(Address addr);

    int updateDefault(String aid, int u_id);
}
