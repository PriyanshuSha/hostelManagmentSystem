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
	public List<room> viewRoom() {
		try(Session ses=HibernateUtil.getSession()){
			
			Query qu=ses.createQuery("from room");
			List<room> roomList=qu.getResultList();
			return roomList;
		}
	}

	@Override
	public List<user> viewUser() {
          try(Session ses=HibernateUtil.getSession()){
			String student="student";
			Query qu=ses.createQuery("from user where userRole=:student").setParameter("student", student);
			List<user> userList=qu.getResultList();
			return userList;
	}}

	@Override
	public List<user> userInARoom(int rId) {
		try(Session ses=HibernateUtil.getSession()){
			
			Query qu=ses.createQuery("from user where userRoom_roomId=:rId").setParameter("rId", rId);
			List<user> userList=qu.getResultList();
			return userList;
		}
	}

	@Override
	public int allotRoom(int rId, int uId) {
		try(Session ses=HibernateUtil.getSession()){
			
			ses.beginTransaction();
			int status=ses.createQuery("update user set userRoom_roomId=:rId where userId=:uId").setParameter("rId", rId).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
			
		}
	}

	@Override
	public int deleteUser(int uId) {
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();
			int status=ses.createQuery("delete from user where userid=:uId").setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
	}}

	@Override
	public int createRoom(room r1) throws GlobalException {
		
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
	public int addUserAmount(int uId, int amount) {
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();	
			int dueAmount=(int)ses.createQuery("Select userFee from user where userId=:uId").setParameter("uId", uId).uniqueResult();
			
			dueAmount+=amount;
			int status=ses.createQuery("update user set userFee=:dueAmount where userId=:uId").setParameter("dueAmount", dueAmount).setParameter("uId", uId).executeUpdate();
			ses.beginTransaction();
			return status;
			
		
	}}

	@Override
	public int paidUserAmount(int uId, int amount) {
		try(Session ses=HibernateUtil.getSession()){
			ses.beginTransaction();	
			int dueAmount=(int)ses.createQuery("Select userFee from user where userId=:uId").setParameter("uId", uId).uniqueResult();
			
			dueAmount-=amount;
			int status=ses.createQuery("update user set userFee=:dueAmount where userId=:uId").setParameter("dueAmount", dueAmount).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		
	}}

	@Override
	public user viewUserProfile(int uId) {
       try(Session ses=HibernateUtil.getSession()){
			
			user u1=ses.get(user.class, uId);
			return u1;
		}
	}
}
