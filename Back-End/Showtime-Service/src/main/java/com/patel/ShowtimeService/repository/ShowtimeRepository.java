package com.patel.ShowtimeService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patel.ShowtimeService.model.Showtime;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

	List<Showtime> findByMovieId(String movieId);

}
