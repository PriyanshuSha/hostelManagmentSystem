package com.hostelMS.daoImpl;

import org.hibernate.Session;
import com.hostelMS.config.HibernateUtil;
import com.hostelMS.dao.userDao;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.user;


  public class userDaoImpl implements userDao {
	
	@Override                                
	public user viewRoom(int uId)  {                             // Here we perform view room related operation 
		try(Session ses=HibernateUtil.getSession()){             // This is a auto closable try block
			user u2=ses.get(user.class,uId);
			return u2;                                           // Here we return one user
		}
		
	}

	
	@Override
	public int viewDueAmount(int uId) {                         // Here we perform view due amount related operation 
		try(Session ses=HibernateUtil.getSession()){            // This is a auto closable try block
			int amount=(int)ses.createQuery("select userFee from user where userId=:uId").setParameter("uId", uId).uniqueResult();
				return amount;                                  // Here we return amount 
		}
	}

	@Override
	public user viewProfile(int uId) {                         // Here we perform view user profile related operation
		try(Session ses=HibernateUtil.getSession()){           // This is a auto closable try block
			user u2=ses.get(user.class,uId);
			return u2;                                         // Here we return one user
	}
	}

	@Override
	public int changePhone(int uId, String phone) {            // Here we perform change phone number related operation
		try(Session ses=HibernateUtil.getSession()){           // This is a auto closable try block
			ses.beginTransaction();                            // Here we begin transaction 
		int status=ses.createQuery("update user set userPhone=:phone where userId=:uId").setParameter("phone", phone).setParameter("uId",uId).executeUpdate();
			ses.getTransaction().commit();                     // Here we commit the transaction
			return status;	                                   // Here we return Status which is caught by ses.createquerry 
		}
	}

	
	@Override
	public int changePassword(int uId, String oldPwd, String newPwd) throws GlobalException { // Here we perform change password related operation
		try(Session ses=HibernateUtil.getSession()){          // This is a auto closable try block    
			ses.beginTransaction();                           // Here we begin transaction 
			user u1=ses.get(user.class, uId);
			if(u1.getUserPassword().equals(oldPwd)) {         // Here we check entered password by old password
				int status =ses.createQuery("update user set userPassword=:newPwd where userId=:uId").setParameter("newPwd", newPwd).setParameter("uId", uId).executeUpdate();
				ses.getTransaction().commit();                // Here we commit the transaction
				return status;                                // Here we return Status which is caught by ses.createquerry 
			}
			else {
				throw new GlobalException("To update password you have to enter current password");
			}                                                 // Here we throws a global exception

		}

	}

}
