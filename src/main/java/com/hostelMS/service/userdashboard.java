package com.hostelMS.service;

import com.hostelMS.exception.GlobalException;

public interface userdashboard {
	
	public void dashboard(int uId) throws GlobalException;
	public void viewRoom();
	public void viewDueAmount();
	public void viewProfile();
	public void changePhonenumber();
	public void changePassword() throws GlobalException;
	

}
