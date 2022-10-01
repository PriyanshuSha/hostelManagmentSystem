/*This is a implementation class of adminDao interface */

package com.hostelMS.daoImpl;

import java.util.List;
import javax.persistence.Query;
import com.hostelMS.config.HibernateUtil;
import com.hostelMS.dao.adminDao;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.room;
import com.hostelMS.model.user;
import org.hibernate.Session;

public class adminDaoImpl implements adminDao{

	@Override
	public List<room> viewRoom() {                                             // Here we perform a view room related operations
		try(Session ses=HibernateUtil.getSession()){
			
			Query qu=ses.createQuery("from room");
			List<room> roomList=qu.getResultList();
			return roomList;
		}
	}

	@Override
	public List<user> viewUser() {                                             // Here we perform a view user related operations
          try(Session ses=HibernateUtil.getSession()){
        	  
			String student="student";
			Query qu=ses.createQuery("from user where userRole=:student").setParameter("student", student);
			List<user> userList=qu.getResultList();
			return userList;
	}}

	@Override
	public List<user> userInARoom(int rId) {                                   // Here we perform a view user in a room related operations
		try(Session ses=HibernateUtil.getSession()){
			
			Query qu=ses.createQuery("from user where userRoom_roomId=:rId").setParameter("rId", rId);
			List<user> userList=qu.getResultList();
			return userList;
		}
	}

	@Override
	public int allotRoom(int rId, int uId) {                                   // Here we perform a allot room related operations
		try(Session ses=HibernateUtil.getSession()){
			
			ses.beginTransaction();
			int status=ses.createQuery("update user set userRoom_roomId=:rId where userId=:uId").setParameter("rId", rId).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
			
		}
	}

	@Override
	public int deleteUser(int uId) {                                           // Here we perform a delete user related operations
		try(Session ses=HibernateUtil.getSession()){
			
			ses.beginTransaction();
			int status=ses.createQuery("delete from user where userid=:uId").setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
	}}

	@Override
	public int createRoom(room r1) throws GlobalException {                    // Here we perform a create room related operations
		try(Session ses=HibernateUtil.getSession()){
			
			ses.beginTransaction();
			String roomName=r1.getRoomName();
			room r2=null;
			
			r2=(room)ses.createQuery("from room where roomName=:roomName").setParameter("roomName", roomName).uniqueResult();
			
			if(r2==null)
			{
				ses.save(r1);
				
				ses.getTransaction().commit();
				return 1;
			}
			else {
				throw new GlobalException("Room is already existed Please enter a different room name ");
			}
		}
	}

	@Override
	public int addUserAmount(int uId, int amount) {                            // Here we perform a add user's due amount related operations
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();	
			int dueAmount=(int)ses.createQuery("Select userFee from user where userId=:uId").setParameter("uId", uId).uniqueResult();
			
			dueAmount+=amount;
			int status=ses.createQuery("update user set userFee=:dueAmount where userId=:uId").setParameter("dueAmount", dueAmount).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
			
		
	}}

	@Override
	public int paidUserAmount(int uId, int amount) {                          // Here we perform a paid user's due amount related operations
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();	
			int dueAmount=(int)ses.createQuery("Select userFee from user where userId=:uId").setParameter("uId", uId).uniqueResult();
			
			dueAmount-=amount;
			int status=ses.createQuery("update user set userFee=:dueAmount where userId=:uId").setParameter("dueAmount", dueAmount).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		
	}}

	@Override
	public user viewUserProfile(int uId) {                                    // Here we perform a view user's Profile related operations
       try(Session ses=HibernateUtil.getSession()){
			
			user u1=ses.get(user.class, uId);
			return u1;
		}
	}
}
