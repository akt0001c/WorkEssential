package com.essential.entites;

import java.time.LocalDateTime;

import com.masaischool.dto.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wages {
	private Integer wagesId;
    private Double amount;
    private Project project;
    private Employee employee;
    private LocalDateTime createdAt;
}
