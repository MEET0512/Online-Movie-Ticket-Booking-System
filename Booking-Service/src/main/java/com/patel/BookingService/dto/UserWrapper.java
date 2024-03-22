package com.patel.BookingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper {

	private Long Id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
}
