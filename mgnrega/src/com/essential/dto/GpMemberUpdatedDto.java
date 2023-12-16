package com.essential.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GpMemberUpdatedDto {
  private String email;
  private String mobno;
  private Date dob;
  
}
