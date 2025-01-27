package com.patel.UserManagementService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

	Long id;
	String firstName;
	String lastName;
	String email;
	String phone;
}
