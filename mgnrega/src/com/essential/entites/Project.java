package com.essential.entites;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	private int projectId;
    private String projectName;
    private GpMember assignedGpMember;
    private List<Employee> assignedEmployees;
    private List<Wages> wagesList;
    private LocalDateTime createdAt;
    private Integer isDeleted;
}
