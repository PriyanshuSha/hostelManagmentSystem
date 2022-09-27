package com.hostelMS.config;

import java.util.Properties;

import com.hostelMS.model.room;
import com.hostelMS.model.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {
	
private static SessionFactory sesFactory;
	
	public static SessionFactory getSessionFactory() {
		
		if(sesFactory==null) {
			
			try {
			
				Configuration config=new Configuration();
				Properties pro=new Properties();
				
			pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");	
			pro.put(Environment.URL,"jdbc:mysql://localhost:3306/hostelMS");	
			pro.put(Environment.USER, "root");
			pro.put(Environment.PASS,"Mukul@321");
			pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
			pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			pro.put(Environment.SHOW_SQL, "false");
			pro.put(Environment.HBM2DDL_AUTO, "update");
			
			config.setProperties(pro);
			config.addAnnotatedClass(user.class);
			config.addAnnotatedClass(room.class);
			
			sesFactory=config.buildSessionFactory();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		return sesFactory;
		
	}
	
	public static Session getSession() {
		return getSessionFactory().openSession();
	}
	

}
