/* Here we perform configuration and connect our application with database */

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
				
			    pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");	        // Here we load our drivers 
			    pro.put(Environment.URL,"jdbc:mysql://localhost:3306/hostelMS");	// Here we set our URL
			    pro.put(Environment.USER, "root");                                  // Here we set our User name of database
			    pro.put(Environment.PASS,"Mukul@321");                              // Here we set our database password  
			    pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");// Here we add our dialect
			    pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			    pro.put(Environment.SHOW_SQL, "false");
			    pro.put(Environment.HBM2DDL_AUTO, "update");
			
			    config.setProperties(pro);
			    config.addAnnotatedClass(user.class);                               // Here we add our annotated class 
			    config.addAnnotatedClass(room.class);                               // Here we add our annotated class  
			
			    sesFactory=config.buildSessionFactory();
			}
			catch(Exception e)                                                      // Here we catch exception 
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
