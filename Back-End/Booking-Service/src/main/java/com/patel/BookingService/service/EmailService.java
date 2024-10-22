package com.patel.BookingService.service;


import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.UrlResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.patel.BookingService.Iservice.IEmailService;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

	private final JavaMailSender mailSender;
	
	@Override
	public void sendBookingConfirmationEmail(String userEmail, String movieName, String movieBannerUrl, String showtime) {
		
		try {
			
			JavaMailSenderImpl mailSenderImpl = (JavaMailSenderImpl) mailSender;
			
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailSenderImpl.getUsername(), mailSenderImpl.getPassword());
				}
			};
			
			Session session = Session.getInstance(mailSenderImpl.getJavaMailProperties(), auth);
			
			MimeMessage message = new MimeMessage(session);
			MimeMessageHelper helper = null;
			
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(new InternetAddress("no-reply@demomailtrap.com"));
			helper.setTo(userEmail);
			helper.setSubject("Booking Confirmation");
			
			// Define the date-time format pattern
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	        // Parse the string into a LocalDateTime object
	        LocalDateTime localDateTime = LocalDateTime.parse(showtime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

	        // Format the LocalDateTime object using the formatter
	        String formattedDateTime = localDateTime.format(formatter);
			
			String emailContent = "Dear User,\n\nYour booking for the following movie has been confirmed:\n\n" +
                    "Movie Name: " + movieName + "\n" +
                    "Showtime: " + formattedDateTime  + "\n\n" +
                    "Thank you for using our Ticket Booking System!";
			
			helper.setText(emailContent);
			
			
			URL url = new URL(movieBannerUrl);
			UrlResource banner = new UrlResource(url);
			helper.addInline("movieBanner", banner, "image/png");
			
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
