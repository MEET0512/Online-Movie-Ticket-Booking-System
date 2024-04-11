package com.patel.UserManagementService.Iservice;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

	String generatedToken(UserDetails  userDetails);
	
	String generateRefreshToken(Map<String, Object> extractClaim,UserDetails userDetails);
	
	String extractUsername(String token);
	
	boolean validateToken(String token, UserDetails userDetails);
}
