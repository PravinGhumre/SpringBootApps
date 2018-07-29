package com.spring.jdbc.jap.SpringBootJdbcJPA;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.spring.jdbc.jpa.Person;
import com.springboot.spring.jdbc.jpa.PersonRepository;
import com.springboot.spring.jdbc.jpa.SpringBootJdbcJpaApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJdbcJpaApplication.class)
@Transactional
public class SpringBootPersonRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonRepository personRepository;

	@Test
	@DirtiesContext
	public void test_findAll() {
		List<Person> persons = personRepository.findAll();
		logger.info(" find_all {} ", persons);
	}

	@Test
	@DirtiesContext
	public void test_findById() {
		Person person = personRepository.findById(10001);
		logger.info(" find_ById {} ", person);
	}

	@Test
	public void test_insert() {
		Person person = new Person(100004, "Pranjal", "Pune", new Date());
		personRepository.insertOrUpdate(person);

		List<Person> persons = personRepository.findAll();
		logger.info(" find_all {} ", persons);
	}

	@Test
	public void test_update() {
		Person person = personRepository.findById(10001);
		person.setName("Ranga Updated !!");

		personRepository.insertOrUpdate(person);

		List<Person> persons = personRepository.findAll();
		logger.info(" find_all {} ", persons);
	}

	@Test
	public void test_delete() {
		Person person = personRepository.findById(10001);
		person.setName("Ranga Updated !!");

		personRepository.deleteById(person);

		List<Person> persons = personRepository.findAll();
		logger.info(" find_all {} ", persons);
	}
}
