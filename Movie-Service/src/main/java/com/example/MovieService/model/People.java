package com.example.MovieService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {

	private String id;
	private String name;
	private String gender;
	private String age;
}
