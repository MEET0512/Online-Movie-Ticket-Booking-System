package com.patel.ShowtimeService.Iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.patel.ShowtimeService.model.Showtime;

@Service
public interface ShowtimeService {

	List<Showtime> getMovieShowtime(String movieId);

	Showtime getShowtime(Long showtimeId);

}
