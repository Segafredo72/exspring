package be.abis.exercise.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import be.abis.exercise.exception.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;

@Service
public interface TrainingService {

	 ArrayList<Person> getAllPersons();
	 public Person findPerson(int id);
	 Person findPerson(String emailAddress, String passWord);
	 void addPerson(Person p) throws IOException;
	 
	 public void deletePerson(int id);
	 public void changePassword(Person p, String newPswd) throws IOException;
	 
	

	 public List<Course> showFollowedCourses(Person person);
	public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException;
		
	public CourseService getCourseService();
	public void setCourseService(CourseService courseService);
}
