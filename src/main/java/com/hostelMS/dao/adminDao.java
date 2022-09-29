package com.hostelMS.dao;

import java.util.List;

import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.*;

public interface adminDao {
	
	public List<room> viewRoom(); 
	public List<user> viewUser(); 
	public List<user> userInARoom(int rId);
	public int allotRoom(int rId,int uId);
	public int deleteUser(int uId);
	public int createRoom(room r1) throws GlobalException;
	public int addUserAmount(int uId,int amount);
	public int paidUserAmount(int uId,int amount);
	public user viewUserProfile(int uId);

}
