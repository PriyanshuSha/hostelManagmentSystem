/*Here we perform testing of some methods of our program those methods are registration, changePassword, admin, login,deleteUser, addUserAmount,paidUserAmount
 *@author Priyanshu  */

package com.hostelMS;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.hostelMS.config.HibernateUtil;
import com.hostelMS.dao.adminDao;
import com.hostelMS.dao.hostelMSDao;
import com.hostelMS.dao.userDao;
import com.hostelMS.daoImpl.adminDaoImpl;
import com.hostelMS.daoImpl.hostelMSDaoImpl;
import com.hostelMS.daoImpl.userDaoImpl;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.room;
import com.hostelMS.model.user;

public class AppTest 
{
	//1st test
	@Test
	@DisplayName("This is a test case for registeraion")
	void registrationTest() throws GlobalException {
		hostelMSDao dao=new hostelMSDaoImpl();
		user u1=new user();                                     // Here we create one user for add user in registration
		u1.setUserName("Akhil");
		u1.setUserPassword("akhil0007");
		u1.setUserPhone("1111222345");
		u1.setUserRole("student");
		u1.setUserAddress("lucknow");
		
		user u2=new user();                                    // Here we create one user for check Exception in registration                          
		u2.setUserName("ram ji");
		u2.setUserPassword("12345678");
		u2.setUserPhone("0987654321");
		u2.setUserRole("student");
		u2.setUserAddress("Gurugram");
		
		assertAll(
				()->assertEquals(1,dao.registration(u1)),                        // Here we check new user add or not 
				()->assertThrows(GlobalException.class,()->dao.registration(u2)) // Here we check program throws exception or not when user already exist
	   	);	
        }
	
	//2nd test
	@Test
	@DisplayName("This is a test case for change Password")
	void changePasswordTest() throws GlobalException {
		userDao udao=new userDaoImpl();                         // Here we create object of userDaoImpl class  
		
		assertAll(
		()->assertEquals(1,udao.changePassword(13,"123456", "12345667")),                 // Here we check users password change or not 
		()->assertThrows(Exception.class,()->udao.changePassword(50,"112233", "1234567"))// Here we check program throws exception or not when user not exist 
		);
	    }
	
	//3rd test
	@Test
	void createRoomTset() throws GlobalException {
		
		adminDao dao=new adminDaoImpl();                       // Here we create object of adminDaoImpl class 
		
		room r1=new room();                                    // Here we create one room for add room 
		r1.setRoomId(111);
		r1.setRoomName("super delux");
		r1.setRoomType("fully AC");
		
		room r2=new room();                                    // Here we create one room for check exception is thrown or not 
		r2.setRoomId(105);
		r2.setRoomName("ossm");
		r2.setRoomType("AC");
		assertAll(
		    ()->assertEquals(1,dao.createRoom(r1)),           // Here we add room in the room table  
		    ()->assertThrows(GlobalException.class,()->dao.createRoom(r2)) // Here we check program throws exception or not when room already exist 
	    );	
	    }	
	
	//4th test
	@Test
	@DisplayName("This is a test case for login testing")
	void loginTest() throws GlobalException {
		
		
		hostelMSDao dao = new hostelMSDaoImpl();             // Here we create object of hostelMSDaoImpl class 
		Session ses = HibernateUtil.getSession();           
	
		user u = ses.get(user.class, 2);
		user a = dao.login("Arun","11223344");
		
		assertAll(
				()->assertEquals(u.toString(),a.toString()), // Here we check user is login or not
				()->assertThrows(GlobalException.class,()->dao.login("Arun", "112233")) // Here we check program throws exception or not when user is not login successfully 
		);
	    }
	
	//5th test
	@Test
	@DisplayName("This is a test case for delete user")
	void deleteUserTest() {
		adminDao dao = new adminDaoImpl();                 // Here we create object of adminDaoImpl class 
		
		assertAll(
				()->assertEquals(1,dao.deleteUser(22)),    // Here we check user is deleted or not
				()->assertEquals(0,dao.deleteUser(100))    // Here we check it return 0 when user is not exist 

	    );
    	}
	 
	//6th test
	@Test
	@DisplayName("This is a test case for add user amount")
	void addUserAmountTest() {
		adminDao dao = new adminDaoImpl();                // Here we create object of adminDaoImpl class 
		assertAll(
				()-> assertEquals(1,dao.addUserAmount(2, 1000)), // Here we add users amount in there account 
				()->assertThrows(Exception.class,()->dao.addUserAmount(50, 500)) // Here we check program throws exception or not when user is not exist  
		);															
	    }
	
	//7th test
	@Test
	@DisplayName("This is a test case for paid user amount")
       void paidUserAmountTest() {
		adminDao dao = new adminDaoImpl();               // Here we create object of adminDaoImpl class 
		assertAll(
				()->assertEquals(1,dao.paidUserAmount(2, 100)),  // Here we reduce users amount in there account 
				()->assertThrows(Exception.class,()->dao.paidUserAmount(50,400)) // Here we check program throws exception or not when user is not exist  
		);
	    }
       }
