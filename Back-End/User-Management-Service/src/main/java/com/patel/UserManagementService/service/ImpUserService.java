package com.patel.UserManagementService.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.patel.UserManagementService.Iservice.JwtService;
import com.patel.UserManagementService.Iservice.UserService;
import com.patel.UserManagementService.dto.ProfileResponse;
import com.patel.UserManagementService.model.User;
import com.patel.UserManagementService.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImpUserService implements UserService {

	private final UserRepository userRepo;
	private final JwtService jwtService;
	
	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
					
					@Override
					public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
						return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
					}
				};
			}

	@Override
	public ProfileResponse getProfile(String token) {
		try {
			String email = jwtService.extractUsername(token);
			Optional<User> user = userRepo.findByEmail(email);
			
			if(user.isEmpty()) {
				return null;
			}
			
			var details = user.get();
			
			ProfileResponse response = new ProfileResponse(details.getId(),details.getFirstName(), details.getLastName(), details.getEmail(), details.getPhone());
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
