/* This is a interface of adminDao implementation class  */

package com.hostelMS.dao;

import java.util.List;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.*;

public interface adminDao {
	
	public List<room> viewRoom();                                     // This is a method for view room 
	public List<user> viewUser();                                     // This is a method for view User 
	public List<user> userInARoom(int rId);                           // This is a method for view user In a Room 
	public int allotRoom(int rId,int uId);                            // This is a method for allot Room 
	public int deleteUser(int uId);                                   // This is a method for delete User 
	public int createRoom(room r1) throws GlobalException;            // This is a method for create Room 
	public int addUserAmount(int uId,int amount);                     // This is a method for add User Amount 
	public int paidUserAmount(int uId,int amount);                    // This is a method for paid User Amount 
	public user viewUserProfile(int uId);                             // This is a method for view User's Profile

}
