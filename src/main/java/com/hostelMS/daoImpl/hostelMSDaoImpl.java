package com.hostelMS.daoImpl;

import org.hibernate.Session;

import com.hostelMS.config.HibernateUtil;
import com.hostelMS.dao.hostelMSDao;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.user;


public class hostelMSDaoImpl implements hostelMSDao {
	@Override
	public int registration(user u1) throws GlobalException {
		
		try(Session ses=HibernateUtil.getSession())
		{
			
			String username=u1.getUserName();
			user u2=null;
			u2=(user)ses.createQuery("from user where userName=:username").setParameter("username", username).uniqueResult();
			if(u2==null)
			{
				ses.beginTransaction();
				ses.save(u1);
				ses.getTransaction().commit();
				return 1;	
			}
			else {
				throw new GlobalException("User already Existed ");
			}
			
		}
		
		
	}

	@Override
	public user login(String username, String password) throws GlobalException {
		
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
			
			user u2=null;
			u2=(user)ses.createQuery("from user where userName=:username").setParameter("username", username).uniqueResult();
			if(u2!=null)
			{
			if(u2.getUserPassword().equals(password)) {
				return u2;
			}
			else {
				throw new GlobalException("Wrong Username or Password....!!");
			}
			}
			else {
				throw new GlobalException("Wrong Username or Password...!!");
			}	
		}	
	}
}
