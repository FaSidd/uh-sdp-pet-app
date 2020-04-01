package com.sdp.petapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import com.sdp.petapi.services.UserService;
import com.sdp.petapi.controllers.AuthController;
import com.sdp.petapi.models.LoginRequest;
import com.sdp.petapi.models.LoginResponse;
import com.sdp.petapi.models.Message;
import com.sdp.petapi.models.Pet;
import com.sdp.petapi.models.SignupRequest;
import com.sdp.petapi.models.User;
import com.sdp.petapi.security.JwtUtils;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
class AuthControllerTest {
	Pet pet;
	User employee, webUser;

	@Mock
	UserService userService;

	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	JwtUtils jwtUtils;

	@Mock
	AuthenticationManager authenticationManager;

	// makes a authController whose userService is the mock above
	@InjectMocks
	AuthController authController;

// 	@Before
//   public void init() {
//     MockitoAnnotations.initMocks(this);
//   }

	@Test
	public void signup_new_user() {
		SignupRequest signUpRequest = new SignupRequest("test@gmail.com", "password", "test name");
		when(userService.existsByEmail(signUpRequest.getEmail())).thenReturn(false);
		when(userService.createUser(webUser)).thenReturn(webUser);
		when(passwordEncoder.encode(signUpRequest.getPassword())).thenReturn("Encoded password");
		Message returnedMessage = authController.registerUser(signUpRequest);
		assertEquals(new Message("User registered successfully!"), returnedMessage);
	}

	@Test
	public void signup_user_already_exists() {
		SignupRequest signUpRequest = new SignupRequest("test@gmail.com", "password", "test name");
		when(userService.existsByEmail(signUpRequest.getEmail())).thenReturn(true);
		Message returnedMessage = authController.registerUser(signUpRequest);
		assertEquals(new Message("Error: Email is already in use!"), returnedMessage);
	}

	@Test
	@WithUserDetails(value = "User", userDetailsServiceBeanName = "TestingUserDetailsService")
	public void signin() {

		LoginRequest loginRequest = new LoginRequest("ironman@mail.com", "");
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication);
		when(jwtUtils.generateJwtToken(authentication)).thenReturn("tokenString");
		
		LoginResponse loginResponse = authController.authenticateUser(loginRequest);
		
		assertEquals("tokenString", loginResponse.getJwt());
		assertEquals("002", loginResponse.getId());
		assertEquals("ironman@mail.com", loginResponse.getEmail());
		assertEquals(Arrays.asList("ROLE_User"), loginResponse.getRoles());
	}


}