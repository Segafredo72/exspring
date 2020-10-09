package be.abis.exercise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.abis.exercise.model.Course;

@Service
public interface CourseService {

	public List<Course> findAllCourses();
	
	public Course findCourse(int id);
	
	public Course findCourse(String shortTitle);
	
}
