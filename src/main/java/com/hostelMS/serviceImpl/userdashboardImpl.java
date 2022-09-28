package com.hostelMS.serviceImpl;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.hostelMS.dao.userDao;
import com.hostelMS.daoImpl.userDaoImpl;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.user;
import com.hostelMS.service.userdashboard;


   public class userdashboardImpl implements userdashboard {
	
	   static Logger log=Logger.getLogger(userdashboardImpl.class);
	   static Scanner bs=new Scanner(System.in);
	   static userdashboardImpl udl=new userdashboardImpl();
	   static userDao dao=new userDaoImpl();
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
		
		case 1->udl.viewRoom();
		
		case 2->udl.viewDueAmount();
		
		case 3->udl.viewProfile();
		
		case 4->udl.changePhonenumber();
		
		case 5->udl.changePassword();
		}
		}
	}

	
	@Override
	public void viewRoom() {
		
	   user u1=dao.viewRoom(userId);
	   log.info("HELLO "+u1.getUserName()+" YOUR ROOM NUMBER IS "+u1.getUserRoom().getRoomId()+" ROOM NAME IS "+u1.getUserRoom().getRoomName()+" AND IT IS "+u1.getUserRoom().getRoomType()+" ROOM ");
	  }

	@Override
	public void viewDueAmount() {
		
	   int amount=dao.viewDueAmount(userId);
       log.info("YOUR FEE DUE UUPTO THIS MONTH IS : "+amount);
	}

	
	@Override
	public void viewProfile() {
		
	   user u1=dao.viewProfile(userId);
	   log.info(u1);
		
	}

	//to change phone number
	@Override
	public void changePhonenumber() {
		log.info("ENTER NEW PHONE NUMBER ");
		String phone=bs.next();
		int st=dao.changePhone(userId, phone);
		if(st==1) {
	    log.info("YOUR PHONE NUMBER IS UPDATED SUCESSFULLY ");
		}
	}

	@Override
	public void changePassword() throws GlobalException {
		
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
