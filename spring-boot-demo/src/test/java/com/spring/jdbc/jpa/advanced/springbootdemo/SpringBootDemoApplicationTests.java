package com.spring.jdbc.jpa.advanced.springbootdemo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jdbc.jpa.advanced.SpringBootDemoApplication;
import com.spring.jdbc.jpa.advanced.entity.Course;
import com.spring.jdbc.jpa.advanced.entity.Employee;
import com.spring.jdbc.jpa.advanced.entity.FullTimeEmployee;
import com.spring.jdbc.jpa.advanced.entity.PartTimeEmployee;
import com.spring.jdbc.jpa.advanced.entity.Passport;
import com.spring.jdbc.jpa.advanced.entity.Review;
import com.spring.jdbc.jpa.advanced.entity.Student;
import com.spring.jdbc.jpa.advanced.repository.CourseRepository;
import com.spring.jdbc.jpa.advanced.repository.EmployeeRepository;
import com.spring.jdbc.jpa.advanced.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
@Transactional
public class SpringBootDemoApplicationTests {

	@Autowired
	private EntityManager em;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void contextLoads() {
		logger.info("context loaded !!");
	}

	@Test
	public void test_findAll() {
		List<Student> students = studentRepository.findAll();
		logger.info("students {} ", students);

		for (Student student : students) {
			logger.info("passport {} ", student.getPassport());
		}
	}

	@Test
	public void test_saveStudentWithPassport() {
		studentRepository.saveStudentWithPassport();

		List<Student> students = studentRepository.findAll();
		logger.info("students {} ", students);

		for (Student student : students) {
			logger.info("passport {} ", student.getPassport());
		}
	}

	@Test
	public void test_addReviewsForCourse() {
		Course course = courseRepository.findById(10003);
		List<Review> reviews = new ArrayList<>();
		reviews.add(new Review(5, "Great Course"));
		reviews.add(new Review(4, "Nice Course"));
		courseRepository.addReviewsForCourse(course.getId(), reviews);
	}

	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent() {
		Passport passport = em.find(Passport.class, 40001L);
		logger.info("passport -> {}", passport);
		logger.info("student -> {}", passport.getStudent());
	}

	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = em.find(Student.class, 20001L);

		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses());
	}

	@Test
	public void test_retrieveAllPartTimeEmployees() {
		List<PartTimeEmployee> retrieveAllPartTimeEmployees = employeeRepository.retrieveAllPartTimeEmployees();
		logger.info("retrieveAllPartTimeEmployees -> {}", retrieveAllPartTimeEmployees);
	}

	@Test
	public void test_retrieveAllFullTimeEmployees() {
		List<FullTimeEmployee> retrieveAllFullTimeEmployees = employeeRepository.retrieveAllFullTimeEmployees();
		logger.info("retrieveAllFullTimeEmployees -> {}", retrieveAllFullTimeEmployees);
	}

	@Test
	public void test_retrieveAllEmployees() {
		List<Employee> retrieveEmployees = employeeRepository.retrieveAllEmployees();
		logger.info("retrieveEmployees -> {}", retrieveEmployees);
	}
}
