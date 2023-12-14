package com.essential.entites;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class GpMember {
	private Integer gpmMemberId;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String username;
    private String password;
    private Bdo associatedBdo;
    private List<Employee>  employees;
    private LocalDateTime CreatedAt;
}
