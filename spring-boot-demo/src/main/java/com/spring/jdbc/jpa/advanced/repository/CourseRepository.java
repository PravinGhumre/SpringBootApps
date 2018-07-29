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
import com.spring.jdbc.jpa.advanced.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Course> findAll() {
		TypedQuery<Course> allCourse = em.createQuery("select c from Course c", Course.class);
		return allCourse.getResultList();
	}

	public Course findById(long id) {
		return em.find(Course.class, id);
	}

	public void deleteById(Course course) {
		em.remove(course);
	}

	public void insertOrUpdate(Course course) {
		if (null == Long.valueOf(course.getId())) {
			em.persist(course);
		} else {
			em.merge(course);
		}
	}
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews) {		
		Course course = findById(courseId);
		logger.info("course.getReviews() -> {}", course.getReviews());
		for(Review review:reviews)
		{			
			//setting the relationship
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
	}

}
