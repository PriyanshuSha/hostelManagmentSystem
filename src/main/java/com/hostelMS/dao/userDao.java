package com.hostelMS.dao;

import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.user;


public interface userDao {
	public user viewRoom(int uId);
	public int viewDueAmount(int uId);
	public user viewProfile(int uId);
	public int changePhone(int uId,String phone);
	public int changePassword(int uId,String oldPwd,String newPwd) throws GlobalException;
}


