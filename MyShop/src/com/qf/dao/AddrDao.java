package com.qf.dao;

import com.qf.entity.Address;

import java.util.List;

public interface AddrDao {
    List<Address> selectByUid(int u_id);

    int addAddress(Address addr);

    int deleteByAid(String aid);

    int updateAddr(Address addr);

    int setNoDefault(int u_id);

    int setDefault(String aid);
}
