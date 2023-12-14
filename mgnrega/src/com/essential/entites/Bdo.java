package com.essential.entites;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bdo {
	private Integer bdoId;
	private String email;
    private String firstName;
    private String lastName;
    private String designation;
    private String password;
    private List<GpMember> assignedGpMembers;
    private LocalDateTime createdAt;
    private Integer isDeleted;
}
