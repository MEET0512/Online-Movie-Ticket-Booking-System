package com.example.MovieService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.MovieService.Iservice.MovieService;
import com.example.MovieService.dto.RequestMovie;
import com.example.MovieService.model.Movie;
import com.example.MovieService.model.People;

@Service
public class ImpMovieService implements MovieService {

	@Value("${api.film-url}")
	private String url;
	
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public List<Movie> getAllMovies() {
		try {
			RequestMovie[] movies = restTemplate.getForObject(url, RequestMovie[].class);
			
			if(movies.length > 0) {
				List<Movie> allMovies = new ArrayList<>();
				for(RequestMovie movie:movies) {

					List<People> people = new ArrayList<People>();
					
					String[] moviePeople = movie.getPeople();
					if(moviePeople != null) {
						for(int i =0; i < moviePeople.length -1; i++) {
							People p = restTemplate.getForObject(moviePeople[i], People.class);

							if(p!=null) {
								people.add(p);
							}
						}
					}
					
					Movie newMovie = Movie.builder()
											.id(movie.getId())
											.title(movie.getTitle())
											.description(movie.getDescription())
											.director(movie.getDirector())
											.producer(movie.getProducer())
											.release_date(movie.getRelease_date())
											.rt_score(movie.getRt_score())
											.movie_banner(movie.getMovie_banner())
											.people(people)
											.build();
					allMovies.add(newMovie);
				}
				
				return allMovies;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Movie getMovieById(String id) {
		try {
			
			RequestMovie movie = restTemplate.getForObject(url.concat("/" + id), RequestMovie.class);
			
			if(movie == null) {
				return null;
			}
			List<People> people = new ArrayList<People>();
			
			String[] moviePeople = movie.getPeople();
			if(moviePeople != null) {
				for(int i =0; i < moviePeople.length -1; i++) {
					People p = restTemplate.getForObject(moviePeople[i], People.class);

					if(p!=null) {
						people.add(p);
					}
				}
			}
			
			Movie newMovie = Movie.builder()
					.id(movie.getId())
					.title(movie.getTitle())
					.description(movie.getDescription())
					.director(movie.getDirector())
					.producer(movie.getProducer())
					.release_date(movie.getRelease_date())
					.rt_score(movie.getRt_score())
					.movie_banner(movie.getMovie_banner())
					.people(people)
					.build();
			
			
			
			return newMovie;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
