package com.example.MovieService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMovie {

	private String id;
	private String title;
	private String original_title;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private String rt_score;
    private String movie_banner;
    private String[] people;
}
