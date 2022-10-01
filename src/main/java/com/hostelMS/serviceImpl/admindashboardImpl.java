package com.hostelMS.serviceImpl;

import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.hostelMS.dao.adminDao;
import com.hostelMS.daoImpl.adminDaoImpl;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.room;
import com.hostelMS.model.user;
import com.hostelMS.service.admindashboard;


public class admindashboardImpl implements admindashboard{
	static Logger log=Logger.getLogger(admindashboardImpl.class);         // This is use for logger implementation
	static Scanner bs=new Scanner(System.in);                             // Here we create a Scanner class object
	static admindashboard adl=new admindashboardImpl();                   // Here we create a admindashboardImpl class object     
	static adminDao dao=new adminDaoImpl();                               // Here we create a adminDaoImpl class object     

	@Override
	public void dashboard() throws GlobalException {
		
		log.info("\t\t\t\t----------WELCOME TO ADMIN DASHBORAD-----------");
		int op=0;
		while(op<10) {
			
			log.info("\n\tPress 1 For View Rooms\t\t\tPress 2 For View Users\n\tPress 3 For Create Rooms\t\tPress 4 For Allot room to user\n\tPress 5 For view user in a room\t\tPress 6 For view user profile\n\tPress 7 for Add Due Amount\t\tPress 8 For Pay Due Amount\n\tPress 9 For delete user");
			op=bs.nextInt();
		
		switch(op) {
			
			case 1->adl.viewRooms();                                      // Here we create a case for viewRooms
			case 2->adl.viewUsers();                                      // Here we create a case for viewUsers
			case 3->adl.createRoom();                                     // Here we create a case for createRoom
			case 4->adl.allotRoom();                                      // Here we create a case for allotRoom
			case 5->adl.userInARoom();                                    // Here we create a case for userInARoom
			case 6->adl.viewUserProfile();                                // Here we create a case for viewUserProfile
			case 7->adl.addDueAmount();                                   // Here we create a case for addDueAmount
			case 8->adl.paidDueAmount();                                  // Here we create a case for paidDueAmount
			case 9->adl.deleteUser();                                     // Here we create a case for deleteUser
			default->System.exit(0);                                      // Here we create a case for exit
		}
		
		
		}
		
	}

	@Override
	public void viewRooms() {                                             // Here we perform a vieRoom related operations
		    List<room> roomList=dao.viewRoom();
		    log.info("\nroom num\t\troomName\t\troomType");
		for(room r:roomList)
			log.info("\t"+r.getRoomId()+"\t\t"+r.getRoomName()+"\t\t"+r.getRoomType());
		
	}

	@Override
	public void viewUsers() {                                             // Here we perform a viewUsers related operations
		    List<user> userList=dao.viewUser();
		    log.info("\nUser Id\t\tUserName\t\tuser Phone\t\tuserRoom");
		for(user u:userList)
			log.info("\t"+u.getUserId()+"\t\t"+u.getUserName()+"\t\t"+u.getUserPhone()+"\t\t"+u.getUserRoom().getRoomId());
		
		
	}

	@Override
	public void createRoom() throws GlobalException {                    // Here we perform a createRoom related operations
		log.info("ENTER ROOM ID");
		int rId=bs.nextInt();
		log.info("ENTER ROOM NAME");
		String rName=bs.next();
		log.info("ENTER ROOM TYPE");
		String rType=bs.next();
		room r1=new room();
		r1.setRoomId(rId);
		r1.setRoomName(rName);
		r1.setRoomType(rType);
		
		try {
			int st=dao.createRoom(r1);
			 if(st==1) {
				 
				 log.info("ROOM CREATED SUCESSFULLY");
			 }	
		}
		catch(Exception e){
			
			     log.info(e.getMessage());
		}
	}

	@Override
	public void allotRoom() throws GlobalException {                          // Here we perform a allotRoom related operations
		log.info("Enter user Id");
		int uid=bs.nextInt();
		log.info("Enter room Id");
		int rId=bs.nextInt();
		int st=dao.allotRoom(rId,uid);
		if(st==1) {
			log.info("Room alloted successfully");
		}
		else {
			throw new GlobalException("Something went wrong");
		}
		
		
	}

	@Override
	public void deleteUser() throws GlobalException {                         // Here we perform a deleteUser related operations
		log.info("Enter user Id to delete");
		int uid=bs.nextInt();
		int st=dao.deleteUser(uid);
		if(st==1) {
			log.info("deleted!...");
		}
		else {
			throw new GlobalException("Something went wrong");
		}
		
	}

	@Override
	public void userInARoom() {                                               // Here we perform a userInARoom related operations
		log.info("Enter Room Id");
		int rid=bs.nextInt();
	List<user> userList=	dao.userInARoom(rid);
	log.info("\nUser Id\t\tUserName\t\tuser Phone");
	for(user u1:userList)
		log.info("\t"+u1.getUserId()+"\t\t"+u1.getUserName()+"\t\t"+u1.getUserPhone());
		
	}

	@Override
	public void addDueAmount() throws GlobalException {                         // Here we perform a addDueAmount related operations
		log.info("Enter Amount to add");
		int amount=bs.nextInt();
		log.info("Enter user Id");
		int uid=bs.nextInt();
		int st=dao.addUserAmount(uid, amount);
		if(st==1) {
		log.info("amount added");
		}
		else {
		throw new GlobalException("Something went wrong");
		}
	}

	@Override
	public void paidDueAmount() throws GlobalException {                          // Here we perform a paidDueAmount related operations
		log.info("Enter Amount to pay");
		int amount=bs.nextInt();
		log.info("Enter user Id");
		int uid=bs.nextInt();
		int st=dao.paidUserAmount(uid, amount);
		if(st==1) {
		log.info("Amount added");
		}
		else {
		throw new GlobalException("Something went wrong");
		}	
	}

	@Override
	public void viewUserProfile() throws GlobalException {                         // Here we perform a viewUserProfile related operations
		log.info("Enter user id");
		int uid=bs.nextInt();
		user u1=dao.viewUserProfile(uid);
		log.info(u1);
	}
	}

