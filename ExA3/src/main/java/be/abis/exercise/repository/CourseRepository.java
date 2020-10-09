package be.abis.exercise.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import be.abis.exercise.model.Course;

@Repository
public interface CourseRepository {

	public List<Course> findAllCourses();
	public Course findCourse(int id);
	public Course findCourse(String shortTitle);
		
}
