package com.patel.BookingService.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.patel.BookingService.Iservice.IBookingService;
import com.patel.BookingService.Iservice.IEmailService;
import com.patel.BookingService.config.MovieClient;
import com.patel.BookingService.config.ShowtimeClient;
import com.patel.BookingService.config.UserManagementClient;
import com.patel.BookingService.dto.BookinRequest;
import com.patel.BookingService.dto.MovieWrapper;
import com.patel.BookingService.dto.ShowtimeWrapper;
import com.patel.BookingService.dto.UserWrapper;
import com.patel.BookingService.model.Booking;
import com.patel.BookingService.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

	private final BookingRepository bookingRepository;
	
	private final UserManagementClient client;
	
	private final ShowtimeClient showtimeClient;
	
	private final MovieClient movieClient;
	
	private final IEmailService emailService;
	
	@Override
	public Booking addNewBooking(String token, BookinRequest bookingRequest) {
		
		try {
			ResponseEntity<?> response = client.getProfile(token);
			
			UserWrapper user = getUserDetails(response);
			if(user != null) {
				Booking newBooking = Booking.builder()
						.userId(user.getId())
						.showtimeId(bookingRequest.getShowtimeId())
						.createdAt(bookingRequest.getCreatedAt())
						.numTickets(bookingRequest.getNumTickets())
						.build();
				
				return bookingRepository.save(newBooking);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private MovieWrapper getMovie(ResponseEntity<?> response) {
		if(response.getStatusCode().is2xxSuccessful()) {
			Object movieObject = response.getBody();
			
			if(movieObject instanceof Map) {
				
				Map<String, Object> movieMap = (Map<String, Object>) movieObject;
				
				MovieWrapper movie = new MovieWrapper(
							(String)movieMap.get("title"),
							(String)movieMap.get("movie_banner")
						);
				
				return movie;
			}
		}
		return null;
	}

	private ShowtimeWrapper getShowtime(ResponseEntity<?> response) {
		if(response.getStatusCode().is2xxSuccessful()) {
			Object showtimeObject = response.getBody();
			
			if(showtimeObject instanceof Map) {
				
				Map<String, Object> showtimeMap = (Map<String, Object>) showtimeObject;
				
				ShowtimeWrapper showtime = new ShowtimeWrapper(((Number)showtimeMap.get("id")).longValue(),
										(String)showtimeMap.get("movieId"), 
										(String)showtimeMap.get("startTime"), 
										((Number)showtimeMap.get("availableSeats")).intValue());
				
				return showtime;
			}
		}
		return null;
	}

	private UserWrapper getUserDetails(ResponseEntity<?> response) {
		if(response.getStatusCode().is2xxSuccessful()) {
			Object userObject = response.getBody();
			
			if(userObject instanceof Map) {
				
				Map<String, Object> userMap = (Map<String, Object>) userObject;
	            
	            UserWrapper user = new UserWrapper();
	            user.setId(((Number) userMap.get("id")).longValue());
	            user.setFirstName((String) userMap.get("firstName"));
	            user.setLastName((String) userMap.get("lastName"));
	            user.setEmail((String) userMap.get("email"));
	            user.setPhone((String) userMap.get("phone"));
				return user;
			}
		}
		
		return null;
	}

	@Override
	public List<Booking> getAllBookings(String token) {
		try {
			UserWrapper user = getUserDetails(client.getProfile(token));
			
			if(user != null) {
				List<Booking> allBookings = bookingRepository.findByUserId(user.getId());
				
				return allBookings;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void sendConfirmationEmail(String token, Booking newBooking) {
		try {
			ResponseEntity<?> response = client.getProfile(token);
			
			UserWrapper user = getUserDetails(response);
			
			ShowtimeWrapper showtime = getShowtime(showtimeClient.getShowtime(newBooking.getShowtimeId()));
			
			if(showtime != null) {
				MovieWrapper movie = getMovie(movieClient.getMovie(showtime.getMovieId()));
				
				if(movie != null) {
					emailService.sendBookingConfirmationEmail(user.getEmail(), movie.getTitle(), movie.getMovie_banner(), showtime.getStartTime());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
