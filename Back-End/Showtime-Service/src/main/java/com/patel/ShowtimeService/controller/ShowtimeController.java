package com.patel.ShowtimeService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patel.ShowtimeService.Iservice.ShowtimeService;
import com.patel.ShowtimeService.model.Showtime;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/showtime")
@RequiredArgsConstructor
public class ShowtimeController {

	private final ShowtimeService showtimeService;
	
	@GetMapping("/{Id}/showtime")
	public ResponseEntity<?> getMovieShowtime(@PathVariable("Id") String movieId) {
		try {
			List<Showtime> showtime = showtimeService.getMovieShowtime(movieId);
			
			if(showtime == null) {
				return new ResponseEntity<>("There are no any shows.", HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(showtime, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("There is problem while fetching show times of movie", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<?> getShowtime(@PathVariable("Id") Long showtimeId) {
		try {
			
			Showtime showtime = showtimeService.getShowtime(showtimeId);
			
			if(showtime == null) {
				return new ResponseEntity<>("There are no any shows.", HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(showtime, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("There is problem while fetching show time", HttpStatus.BAD_REQUEST);
		}
	}
}
