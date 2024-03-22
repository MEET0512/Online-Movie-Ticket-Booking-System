package com.example.MovieService.Iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.MovieService.model.Movie;

@Service
public interface MovieService {

	List<Movie> getAllMovies();

	Movie getMovieById(String id);

}
