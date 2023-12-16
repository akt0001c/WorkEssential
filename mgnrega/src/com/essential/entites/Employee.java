package com.essential.entites;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	private Integer empno;
	private String empId;
	private String fname;
	private String lname;
	private String gender;
	private String email;
	private String mobno;
	private String pasword;
	private String position;
	private GpMember assignedGpmMember;
	private List<Wages> wagesList;
	private List<Project> projects;
	private LocalDateTime createdAt;
	private Integer isDeleted;
	
}
