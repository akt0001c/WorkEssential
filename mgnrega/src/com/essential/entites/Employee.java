package com.essential.entites;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private String empId;
	private String fname;
	private String lname;
	private String email;
	private String pasword;
	
	private Date createdAt;
	
}
