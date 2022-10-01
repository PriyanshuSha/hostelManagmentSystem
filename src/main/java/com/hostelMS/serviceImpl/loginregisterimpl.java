package com.hostelMS.serviceImpl;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
	
	
	public void register() throws GlobalException{             // Here we perform a register related operations
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
		
		
			
		ValidatorFactory vf= Validation.buildDefaultValidatorFactory();
		Validator valid=vf.getValidator();
		
		Set<ConstraintViolation<user>> violations=	valid.validate(u1);
		
		if(violations.size()>0)
		{
			for(ConstraintViolation<user> violate:violations)
				log.info(violate.getMessage());
		}
		else {
		int status=dao.registration(u1);
			
			if(status==1) {
				log.info("Registration success");
			}
			else {
				throw new GlobalException("Something went wrong");
			}
		}
	}


	public void login()throws GlobalException {                          // Here we perform a login related operations
		
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
