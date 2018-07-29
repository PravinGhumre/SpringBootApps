package com.springboot.spring.jdbc.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

	@Autowired
	EntityManager em;

	public List<Person> findAll() {
		TypedQuery<Person> allPersons = em.createQuery("select p from Person p", Person.class);
		return allPersons.getResultList();
	}

	public Person findById(int id) {
		return em.find(Person.class, id);
	}

	public void deleteById(Person person) {
		em.remove(person);
	}

	public void insertOrUpdate(Person person) {
		if (null == Integer.valueOf(person.getId())) {
			em.persist(person);
		} else {
			em.merge(person);
		}
	}
}
