package com.hostelMS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity                                      // We use this annotation for create entity in the table
@Getter                                      // We use this annotation for get values
@Setter                                      // We use this annotation for set values
@ToString
public class user {
	@Id                                               // We use this annotation for create primary key in the table
	@GeneratedValue(strategy=GenerationType.IDENTITY) // We use this annotation for generation of user ID automatically
	private int userId;
	@NotNull                                          // We use this annotation for give a message to the user is value should not be null 
	@Size(min=3,max=15,message="username must be more than 3 chars and upto 15chars")     // Here we add some validation to userName
	private String userName;
	@NotNull
	@Pattern(regexp="[0-9]{10}",message="phone number must be 10 digits")                 // Here we add some validation to userPhone
	private String userPhone;
	@NotNull
	@Size(min=5,max=20,message="password should be more than 5 chars")                    // Here we add some validation to userPassword
	private String userPassword;
	@NotNull
	@Size(min=4,max=20,message="address should be more than 4 chars")                     // Here we add some validation to userAddress
	private String userAddress;
	private String userRole;
	private int userFee;
	
	@ManyToOne                                                                            // We use this annotation for represent many to one relation                                                               
	private room userRoom;                                                                // here we apply many to one relation
}
