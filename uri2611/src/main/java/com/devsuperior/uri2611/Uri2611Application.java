package com.devsuperior.uri2611;

import com.devsuperior.uri2611.dto.MovieDTO;
import com.devsuperior.uri2611.projections.MovieProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println();
		System.out.println();
		System.out.println("Search with pure SQL");

		List<MovieProjection> result = movieRepository.search1("action");
		List<MovieDTO> movieDTOS = result.stream().map(MovieDTO::new).collect(Collectors.toList());

		for(MovieDTO dto : movieDTOS) {
			System.out.println(dto.toString());
		}

		System.out.println();
		System.out.println();
		System.out.println("Search with JPQL");

		List<MovieDTO> result2 = movieRepository.search2("action");
		for (MovieDTO dto2 : result2) {
			System.out.println(dto2.toString());
		}
	}
}
