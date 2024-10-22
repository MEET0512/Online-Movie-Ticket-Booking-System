package com.example.MovieService.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MovieService.Iservice.MovieService;
import com.example.MovieService.model.Movie;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

	private final MovieService movieService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getCurrentMovies() {
		List<Movie> movies = movieService.getAllMovies();
		
		if(movies == null) {
			return new ResponseEntity<>("There are no movies found", HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(movies);
	}
	
	@GetMapping("/{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getMovie(@PathVariable("Id") String Id) {
		try {
			Movie movie = movieService.getMovieById(Id);
			
			if(movie == null) {
				return new ResponseEntity<>("There is some problem while fetching movie details.", HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(movie, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("There is some problem while fetching movie details.", HttpStatus.BAD_REQUEST);
		}
	}
}
