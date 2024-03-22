package com.patel.ShowtimeService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.patel.ShowtimeService.Iservice.ShowtimeService;
import com.patel.ShowtimeService.model.Showtime;
import com.patel.ShowtimeService.repository.ShowtimeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IshowtimeService implements ShowtimeService {

	private final ShowtimeRepository showtimeRepository;
	
	@Override
	public List<Showtime> getMovieShowtime(String movieId) {
		try {
			List<Showtime> allShows = showtimeRepository.findByMovieId(movieId);
			
			if(allShows == null) {
				return null;
			}
			
			return allShows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Showtime getShowtime(Long showtimeId) {
		try {
			Optional<Showtime> show = showtimeRepository.findById(showtimeId);
			
			if(show.isPresent()) {
				return show.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
