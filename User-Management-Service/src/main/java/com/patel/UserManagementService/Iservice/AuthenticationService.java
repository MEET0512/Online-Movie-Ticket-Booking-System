package com.patel.UserManagementService.Iservice;

import com.patel.UserManagementService.dto.JwtAuthenticationResponse;
import com.patel.UserManagementService.dto.SignInRequest;
import com.patel.UserManagementService.dto.SingUpRquest;
import com.patel.UserManagementService.model.User;

public interface AuthenticationService {

	User signUp(SingUpRquest singUpRquest);
	
	JwtAuthenticationResponse signIn(SignInRequest signInRequest);
	
}
