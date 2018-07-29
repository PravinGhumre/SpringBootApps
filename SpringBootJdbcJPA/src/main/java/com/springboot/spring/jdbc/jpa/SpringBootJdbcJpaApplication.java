package com.springboot.spring.jdbc.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJdbcJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcJpaApplication.class, args);
	}

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonJdbcDAO personDAO;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("inside run method !!");

		/*
		Person person = personDAO.findById(10001);
		person.setName("Ranga Updated !!");

		int rowDeleted = personDAO.deleteById(10001);
		logger.info(" rowInserted {} ", rowDeleted);

		List<Person> persons = personDAO.findAll();
		logger.info(" find_all {} ", persons);
		 */
	}
}
