package com.essential.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BdoUpdatedDto {
  String email;
  String firstName;
  String lastName;
}
