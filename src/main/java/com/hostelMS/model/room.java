package com.hostelMS.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity                       // We use this annotation for create entity in the table
@Getter                       // We use this annotation for get values
@Setter                       // We use this annotation for set values
@ToString
public class room {
	@Id                       // We use this annotation for create primary key in the table
	private int roomId;       // Here we create one instance variable as roomId
	private String roomName;  // Here we create one instance variable as roomName
	private String roomType;  // Here we create one instance variable as roomType
}
