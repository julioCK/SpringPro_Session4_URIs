package com.devsuperior.uri2602;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//search1: busca com projection e SQL puro
		//List<CustomerMinProjection> list = customerRepository.search1("RS");
		//List<CustomerMinDTO> result = list.stream().map(x -> new CustomerMinDTO(x)).collect(Collectors.toList());

		//search2: busca direto com DTO e JPQL
		List<CustomerMinDTO> result = customerRepository.search2("rS");

		for(CustomerMinDTO c : result) {
			System.out.println(c.toString());
		}
	}
}
