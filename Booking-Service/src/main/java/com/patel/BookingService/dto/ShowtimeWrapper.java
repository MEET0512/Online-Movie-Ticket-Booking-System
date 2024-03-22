package com.patel.BookingService.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeWrapper {

	private Long Id;
	private String movieId;
	private String startTime;
	private int availableSeats;
}
