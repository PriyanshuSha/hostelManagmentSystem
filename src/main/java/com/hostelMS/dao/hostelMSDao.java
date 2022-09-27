package com.hostelMS.dao;

import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.user;

public interface hostelMSDao {
	public int registration(user u1) throws GlobalException;
	public user login(String username,String password) throws GlobalException;

}
