package com.patel.BookingService.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookinRequest {

	private Long showtimeId;
	private int numTickets;
	private Date createdAt;
}
