package com.essential.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdatedDto {

	private String fname;
	private String lname;
	private String email;
	private String mobno;
	private String position;
}
