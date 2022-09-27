package com.hostelMS;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.hostelMS.exception.GlobalException;
import com.hostelMS.service.loginregister;
import com.hostelMS.serviceImpl.loginregisterimpl;
/**
 * Hello world!
 *
 */
public class App 
{
	static Logger log=Logger.getLogger(App.class);
    public static void main( String[] args )throws GlobalException
    {
    	Scanner bs=new Scanner(System.in);
    	log.info("\t\t\t\t\t---------WELCOME HOSTEL MANAGMENT SYSTEM----------");
    	loginregister loginreg=new loginregisterimpl();
    	log.info("\nPress 1. For Registeration\nPress 2. For Login");
    	int op=bs.nextInt();
    	
    	switch(op) {
    	case 1->loginreg.register();
    	case 2->loginreg.login();
    	}
    }
}
