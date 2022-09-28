package com.hostelMS.daoImpl;

import org.hibernate.Session;

import com.hostelMS.config.HibernateUtil;
import com.hostelMS.dao.userDao;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.user;


public class userDaoImpl implements userDao {
	
	@Override
	public user viewRoom(int uId)  {
		
		try(Session ses=HibernateUtil.getSession()){
			
			user u2=ses.get(user.class,uId);
			return u2;
		}
		
	}

	//getting dueamount
	@Override
	public int viewDueAmount(int uId) {
	//autoclosable session
		try(Session ses=HibernateUtil.getSession()){
		
			int amount=(int)ses.createQuery("select userFee from user where userId=:uId").setParameter("uId", uId).uniqueResult();
				return amount;
		}
	}

	//getting profile of the user
	@Override
	public user viewProfile(int uId) {
		try(Session ses=HibernateUtil.getSession()){
			
			user u2=ses.get(user.class,uId);
			return u2;
	}
	}
	//updating phone number
	@Override
	public int changePhone(int uId, String phone) {
		
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
		int status=ses.createQuery("update user set userPhone=:phone where userId=:uId").setParameter("phone", phone).setParameter("uId",uId).executeUpdate();
			ses.getTransaction().commit();
			return status;	
		}
	}

	//updating password if current password is correct
	@Override
	public int changePassword(int uId, String oldPwd, String newPwd) throws GlobalException {
		
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
			user u1=ses.get(user.class, uId);
			if(u1.getUserPassword().equals(oldPwd)) {
				int status =ses.createQuery("update user set userPassword=:newPwd where uId=:uId").setParameter("newPwd", newPwd).setParameter("uId", uId).executeUpdate();
				ses.getTransaction().commit();
				return status;
			}
			else {
				throw new GlobalException("To update password you have to enter current password");
			}

		}

	}

}
