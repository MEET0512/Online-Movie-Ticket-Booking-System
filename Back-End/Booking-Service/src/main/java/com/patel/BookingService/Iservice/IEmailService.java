package com.patel.BookingService.Iservice;

public interface IEmailService {
	void sendBookingConfirmationEmail(String userEmail, String movieName, String movieBannerUrl, String showtime);
}
