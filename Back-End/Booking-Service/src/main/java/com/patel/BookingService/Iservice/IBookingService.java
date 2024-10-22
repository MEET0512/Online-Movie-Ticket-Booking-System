package com.patel.BookingService.Iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patel.BookingService.dto.BookinRequest;
import com.patel.BookingService.model.Booking;

@Service
public interface IBookingService {

	Booking addNewBooking(String token, BookinRequest bookingRequest);

	List<Booking> getAllBookings(String token);

	void sendConfirmationEmail(String token,Booking newBooking);

}
