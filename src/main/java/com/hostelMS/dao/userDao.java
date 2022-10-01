/*This is a interface of userDao implementation class */

package com.hostelMS.dao;

import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.user;

public interface userDao {
	
	public user viewRoom(int uId);                        // This is a method for view room 
	public int viewDueAmount(int uId);                    // This is a method for view Due Amount 
	public user viewProfile(int uId);                     // This is a method for view Profile 
	public int changePhone(int uId,String phone);         // This is a method for change Phone 
	public int changePassword(int uId,String oldPwd,String newPwd) throws GlobalException;
                                                       	  // This is a method for change Password 
}


