package com.patel.BookingService.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@FeignClient("MOVIE-SERVICE")
public interface MovieClient {

	@GetMapping("/api/movies/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getMovie(@PathVariable("Id") String Id);
}
