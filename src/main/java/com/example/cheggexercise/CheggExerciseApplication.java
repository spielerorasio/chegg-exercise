package com.example.cheggexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@SpringBootApplication
@EnableAsync
public class CheggExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheggExerciseApplication.class, args);
	}

}
