package com.spring.jdbc.jpa.advanced.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jdbc.jpa.advanced.entity.Course;
import com.spring.jdbc.jpa.advanced.entity.Passport;
import com.spring.jdbc.jpa.advanced.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@Autowired
	EntityManager em;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Student> findAll() {
		TypedQuery<Student> allStudents = em.createQuery("select s from Student s", Student.class);
		return allStudents.getResultList();
	}

	public Student findById(int id) {
		return em.find(Student.class, id);
	}

	public void deleteById(Student student) {
		em.remove(student);
	}

	public void insertOrUpdate(Student student) {
		if (null == Long.valueOf(student.getId())) {
			em.persist(student);
		} else {
			em.merge(student);
		}
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);

		Student student = new Student("Mike");

		student.setPassport(passport);
		em.persist(student);
	}

	public void someOperationToUnderstandPersistenceContext() {
		// Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		// Persistence Context (student)

		// Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();
		// Persistence Context (student, passport)

		// Database Operation 3 - update passport
		passport.setNumber("E123457");
		// Persistence Context (student, passport++)

		// Database Operation 4 - update student
		student.setName("Ranga - updated");
		// Persistence Context (student++ , passport++)
	}

	public void insertHardcodedStudentAndCourse() {
		Student student = new Student("Jack");
		Course course = new Course("Microservices in 100 Steps");
		em.persist(student);
		em.persist(course);

		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);
	}

	public void insertStudentAndCourse(Student student, Course course) {
		// Student student = new Student("Jack");
		// Course course = new Course("Microservices in 100 Steps");
		student.addCourse(course);
		course.addStudent(student);

		em.persist(student);
		em.persist(course);
	}

}
