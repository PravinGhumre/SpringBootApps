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
import com.springboot.spring.jdbc.jpa.PersonJdbcDAO;
import com.springboot.spring.jdbc.jpa.SpringBootJdbcJpaApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootJdbcJpaApplication.class)
@Transactional
public class SpringBootJdbcTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonJdbcDAO personDAO;

	@Test
	@DirtiesContext
	public void test_findAll() {
		List<Person> persons = personDAO.findAll();
		logger.info(" find_all {} ", persons);
	}

	@Test
	@DirtiesContext
	public void test_findById() {
		Person person = personDAO.findById(10001);
		logger.info(" find_ById {} ", person);
	}

	@Test
	public void test_insert() {
		Person person = new Person(100004, "Pranjal", "Pune", new Date());
		int rowInserted = personDAO.insert(person);
		logger.info(" rowInserted {} ", rowInserted);

		List<Person> persons = personDAO.findAll();
		logger.info(" find_all {} ", persons);
	}

	@Test
	public void test_update() {
		Person person = personDAO.findById(10001);
		person.setName("Ranga Updated !!");

		int rowUpdated = personDAO.update(person);
		logger.info(" rowInserted {} ", rowUpdated);

		List<Person> persons = personDAO.findAll();
		logger.info(" find_all {} ", persons);
	}

	@Test
	public void test_delete() {
		Person person = personDAO.findById(10001);
		person.setName("Ranga Updated !!");

		int rowDeleted = personDAO.deleteById(10001);
		logger.info(" rowInserted {} ", rowDeleted);

		List<Person> persons = personDAO.findAll();
		logger.info(" find_all {} ", persons);
	}
}
