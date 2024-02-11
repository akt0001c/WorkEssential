package com.essential.entites;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpMember {
	private Integer gpmMemberId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobno;
    private String gender;
    private LocalDate dob;
    private String password;
    private Bdo associatedBdo;
    private List<Employee>  employees;
    private LocalDateTime CreatedAt;
    private Integer isDeleted;
}
