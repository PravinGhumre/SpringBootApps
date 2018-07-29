package com.spring.jdbc.jpa.advanced.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private Long id;

	private Integer rating;

	private String description;

	@ManyToOne
	private Course course;

	public Review() {
		super();
	}

	public Review(Integer rating, String description) {
		super();
		this.rating = rating;
		this.description = description;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Review [id=%s, rating=%s, description=%s, course=%s]", id, rating, description, course);
	}
}
