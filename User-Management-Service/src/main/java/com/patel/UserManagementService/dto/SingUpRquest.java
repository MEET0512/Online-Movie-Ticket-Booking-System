package com.patel.UserManagementService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingUpRquest {

	String firstName;
	String lastName;
	String Email;
	String password;
	String phone;
}