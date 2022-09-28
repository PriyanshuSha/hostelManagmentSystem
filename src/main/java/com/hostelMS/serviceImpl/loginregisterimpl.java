package com.hostelMS.serviceImpl;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.hostelMS.App;
import com.hostelMS.dao.hostelMSDao;
import com.hostelMS.daoImpl.hostelMSDaoImpl;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.user;
import com.hostelMS.service.admindashboard;
import com.hostelMS.service.loginregister;
import com.hostelMS.service.userdashboard;



    public class loginregisterimpl implements loginregister {
	    static Logger log=Logger.getLogger(App.class);
	    static Scanner bs=new Scanner(System.in);
	    static hostelMSDao dao=new hostelMSDaoImpl();
	
	
	public void register() throws GlobalException{
		log.info("welcome to registeration");
		log.info("Enter Username");
		String uname=bs.next();
		log.info("Create Password");
		String upwd=bs.next();
		log.info("Enter Phone number");
		String uphone=bs.next();
		log.info("Enter Address");
		String uaddress=bs.next();
		
		user u1=new user();
		u1.setUserName(uname);
		u1.setUserPassword(upwd);
		u1.setUserPhone(uphone);
		u1.setUserAddress(uaddress);
		u1.setUserRole("student");
		u1.setUserRoom(null);
		u1.setUserFee(0);
		
		if(Pattern.matches("[a-zA-Z]{4,}", uname)&&Pattern.matches("[a-zA-Z0-9@#]{6,}",upwd)&&Pattern.matches("[0-9]{10}", uphone))
		{
			
			int status=dao.registration(u1);
			if(status==1) {
				log.info("Registration success");
			}
			else {
				throw new GlobalException("Something went wrong...!!");
			}
		    }
		    else {
			    throw new GlobalException("Invalid data Please Enter a valid data ");
		    }
}

	public void login()throws GlobalException {
		
		log.info("welcome to Login");
		log.info("Please Enter Your Username : ");
		String username=bs.next();
		log.info("Please Enter Your Password : ");
		String password=bs.next();
		user u1=dao.login(username, password);
		log.info("Hello "+u1.getUserName()+" Login Successfull Welcome to Hostel ");
		
		userdashboard udl=new userdashboardImpl();
		admindashboard adl=new admindashboardImpl();
		
		
		if(u1.getUserRole().equals("student")) {
			udl.dashboard(u1.getUserId());
		}
		else if(u1.getUserRole().equals("admin")) {
			adl.dashboard();
		}
	}
}
