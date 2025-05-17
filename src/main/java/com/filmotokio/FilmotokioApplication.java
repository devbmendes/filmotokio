package com.filmotokio;


import com.filmotokio.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class FilmotokioApplication{

	@Autowired
	private UserImpl userImpl;

	public static void main(String[] args) {

		SpringApplication.run(FilmotokioApplication.class, args);
	}


}

