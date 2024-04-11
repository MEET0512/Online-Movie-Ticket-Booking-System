package com.patel.BookingService.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SHOWTIME-SERVICE")
public interface ShowtimeClient {

	@GetMapping("/api/showtime/{Id}")
	public ResponseEntity<?> getShowtime(@PathVariable("Id") Long showtimeId);
}
