package com.hostelMS.serviceImpl;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.hostelMS.dao.userDao;
import com.hostelMS.daoImpl.userDaoImpl;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.user;
import com.hostelMS.service.userdashboard;


   public class userdashboardImpl implements userdashboard {
	
	   static Logger log=Logger.getLogger(userdashboardImpl.class);           // This is use for logger implementation
	   static Scanner bs=new Scanner(System.in);                              // Here we create a Scanner class object
	   static userdashboardImpl udl=new userdashboardImpl();                  // Here we create a userdashboardImpl class object
	   static userDao dao=new userDaoImpl();                                  // Here we create a userDaoImpl class object
	   static int userId;
	   
	@Override
	public void dashboard(int uId) throws GlobalException {
		log.info("\t\t\t\t---------------------WELCOME TO USERDASHBOARD----------------------");
		int op=0;
		userId=uId;
		while(op<6) {
	
		log.info("\nPress 1 for viewRoom\nPress 2 for view dueAmount \nPress 3 for view profile\nPress 4 for Update Phone number \nPress 5 for Change password");
			
		op=bs.nextInt();
			
		switch(op) {
		
		case 1->udl.viewRoom();                                              // Here we create a case for viewRooms
		case 2->udl.viewDueAmount();                                         // Here we create a case for viewDueAmount
		case 3->udl.viewProfile();                                           // Here we create a case for viewProfile
		case 4->udl.changePhonenumber();                                     // Here we create a case for changePhonenumber
		case 5->udl.changePassword();                                        // Here we create a case for changePassword
		}
		}
	}

	
	@Override
	public void viewRoom() {                                                // Here we perform a vieRoom related operations
		
	   user u1=dao.viewRoom(userId);
	   log.info("HELLO "+u1.getUserName()+" YOUR ROOM NUMBER IS "+u1.getUserRoom().getRoomId()+" ROOM NAME IS "+u1.getUserRoom().getRoomName()+" AND IT IS "+u1.getUserRoom().getRoomType()+" ROOM ");
	  }

	@Override
	public void viewDueAmount() {                                           // Here we perform a viewDueAmount related operations
		
	   int amount=dao.viewDueAmount(userId);
       log.info("YOUR FEE DUE UUPTO THIS MONTH IS : "+amount);
	}

	
	@Override
	public void viewProfile() {                                             // Here we perform a viewProfile related operations
		
	   user u1=dao.viewProfile(userId);
	   log.info(u1);
		
	}

	@Override
	public void changePhonenumber() {                                      // Here we perform a changePhonenumber related operations
		log.info("ENTER NEW PHONE NUMBER ");
		String phone=bs.next();
		int st=dao.changePhone(userId, phone);
		if(st==1) {
	    log.info("YOUR PHONE NUMBER IS UPDATED SUCESSFULLY ");
		}
	}

	@Override
	public void changePassword() throws GlobalException {                 // Here we perform a changePassword related operations
		
		log.info("PLEASE ENTER YOUR CURRENT PASSWORD ");
		String oldpwd=bs.next();
		log.info("PLEASE ENTER YOUR NEW PASSWORD ");
		String newpwd=bs.next();
		int st=dao.changePassword(userId, oldpwd, newpwd);
		if(st==1) {
		log.info("PASSWORD CHANGE SUCESSFULLY ");
		}
	}
}
