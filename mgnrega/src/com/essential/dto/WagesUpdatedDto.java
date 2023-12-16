package com.essential.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WagesUpdatedDto {

	private Double amount;
	private Integer projectId;
	private Integer employeeId;
}
