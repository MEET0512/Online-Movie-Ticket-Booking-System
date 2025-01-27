package com.patel.BookingService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.BookingService.Iservice.IBookingService;
import com.patel.BookingService.dto.BookinRequest;
import com.patel.BookingService.dto.CustomResponse;
import com.patel.BookingService.model.Booking;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

	private final IBookingService bookingService;
	
	@PostMapping("/add")
	public ResponseEntity<CustomResponse<Booking>> createBooking(@RequestHeader("Authorization") String token,@RequestBody BookinRequest bookingRequest) {
		try {
			Booking newBooking = bookingService.addNewBooking(token, bookingRequest);
			
			if(newBooking != null) {
				bookingService.sendConfirmationEmail(token, newBooking);
				
				CustomResponse<Booking> response = new CustomResponse<>();
				response.setEntity(newBooking);
				response.setMessage("Confirmation Email is send to your email.");
				
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			
			CustomResponse<Booking> response = new CustomResponse<>();
			response.setEntity(null);
			response.setMessage("There is some problem while processing booking.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			CustomResponse<Booking> response = new CustomResponse<>();
			response.setEntity(null);
			response.setMessage("There is some problem while processing booking.");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getbookings")
	public ResponseEntity<?> getAllBookings(@RequestHeader("Authorization") String token) {
		try {
			List<Booking> allBookings = bookingService.getAllBookings(token);
			
			if(allBookings == null) {
				return new ResponseEntity<>("There is no any booking found.", HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(allBookings, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("There is some problem while fetching booking.", HttpStatus.BAD_REQUEST);
		}
	}
}
