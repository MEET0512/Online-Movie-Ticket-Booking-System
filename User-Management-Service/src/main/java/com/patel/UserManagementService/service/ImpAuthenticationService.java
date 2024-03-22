package com.patel.UserManagementService.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patel.UserManagementService.Iservice.AuthenticationService;
import com.patel.UserManagementService.Iservice.JwtService;
import com.patel.UserManagementService.dto.JwtAuthenticationResponse;
import com.patel.UserManagementService.dto.SignInRequest;
import com.patel.UserManagementService.dto.SingUpRquest;
import com.patel.UserManagementService.errors.UserAlreadyExistsException;
import com.patel.UserManagementService.model.Role;
import com.patel.UserManagementService.model.User;
import com.patel.UserManagementService.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImpAuthenticationService implements AuthenticationService {

	private final UserRepository userRepo;
	private final PasswordEncoder encoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	@Override
	public User signUp(SingUpRquest singUpRquest) {
		Optional<User> existedUser = userRepo.findByEmail(singUpRquest.getEmail());
		
		if(existedUser.isPresent()) {
			throw new UserAlreadyExistsException("User with username '" + singUpRquest.getEmail() + "' already exists.");
		}
		
		try {
			User newUser = User.builder()
								.firstName(singUpRquest.getFirstName())
								.lastName(singUpRquest.getLastName())
								.email(singUpRquest.getEmail())
								.password(encoder.encode(singUpRquest.getPassword()))
								.phone(singUpRquest.getPhone())
								.role(Role.ROLE_USER)
								.build();
			
			userRepo.save(newUser);
			
			return newUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
			var user = userRepo.findByEmail(signInRequest.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
			String token = jwtService.generatedToken(user);
			var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
			
			JwtAuthenticationResponse authenticationResponse = JwtAuthenticationResponse.builder()
																						.token(token)
																						.refreshToken(refreshToken)
																						.build();
			
			return authenticationResponse;
	}

}
