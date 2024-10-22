package com.example.MovieService.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	private String id;
	private String title;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String rt_score;
    private String movie_banner;
    private List<People> people;
}
