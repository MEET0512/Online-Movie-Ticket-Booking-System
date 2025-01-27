package com.patel.BookingService.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-MANAGEMENT-SERVICE")
public interface UserManagementClient {

	@GetMapping("/api/auth/profile")
	public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token);
}
