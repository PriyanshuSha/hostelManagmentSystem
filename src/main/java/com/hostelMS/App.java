/* This is a java program in which we perform a transaction's related to hostel Management System here we have a two dashboard user dashboard and admin dashboard 
 * and here we perform login and  testing also 
 * @author Priyanshu */

package com.hostelMS;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.service.loginregister;
import com.hostelMS.serviceImpl.loginregisterimpl;

public class App 
{
	static Logger log=Logger.getLogger(App.class);
    public static void main( String[] args )throws GlobalException
    {
    	Scanner bs=new Scanner(System.in);
    	log.info("\t\t\t\t\t---------WELCOME HOSTEL MANAGMENT SYSTEM----------");
    	
    	loginregister loginreg=new loginregisterimpl();
    	
    	log.info("\nPRESS 1. For Registeration\nPRESS 2. For Login");
    	
    	int op=bs.nextInt();                                                        // Here we read user transaction input
    	
    	switch(op) {
    	case 1->loginreg.register();                                                // Here we call a register method
    	case 2->loginreg.login();                                                   // Here we call a login method 
    	}
    }
}
