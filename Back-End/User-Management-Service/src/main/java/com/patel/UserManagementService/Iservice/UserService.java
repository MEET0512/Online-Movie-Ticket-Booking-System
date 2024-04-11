package com.patel.UserManagementService.Iservice;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.patel.UserManagementService.dto.ProfileResponse;


@Service
public interface UserService {

	UserDetailsService userDetailsService();

	ProfileResponse getProfile(String token);
}
