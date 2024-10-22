package com.patel.UserManagementService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.UserManagementService.Iservice.AuthenticationService;
import com.patel.UserManagementService.Iservice.UserService;
import com.patel.UserManagementService.dto.JwtAuthenticationResponse;
import com.patel.UserManagementService.dto.ProfileResponse;
import com.patel.UserManagementService.dto.SignInRequest;
import com.patel.UserManagementService.dto.SingUpRquest;
import com.patel.UserManagementService.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

	private final UserService userService;
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> SingUp(@RequestBody SingUpRquest singUpRquest) {
		User user = authenticationService.signUp(singUpRquest);
		
		if(user != null) {
			return ResponseEntity.ok(user);
		}
		
		return ResponseEntity.badRequest().body("There are some problem while register new user");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> SingIn(@RequestBody SignInRequest signInRequest){
		JwtAuthenticationResponse response = authenticationService.signIn(signInRequest);
		
		if(response == null) {
			return ResponseEntity.badRequest().body("Username or password is not correct");
		}
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token){
		ProfileResponse response = userService.getProfile(token.substring(7));
		
		if(response == null) {
			return new ResponseEntity<>("There are some problem while fetchin user details", HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(response);
	}
}
