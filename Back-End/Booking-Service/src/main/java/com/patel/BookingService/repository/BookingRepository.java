package com.patel.BookingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patel.BookingService.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByUserId(Long id);

}
