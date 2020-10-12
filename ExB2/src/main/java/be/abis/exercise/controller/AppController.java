package be.abis.exercise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.CourseRepository;
import be.abis.exercise.service.AbisTrainingService;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	
	@Autowired
	TrainingService trainingService;
	 
	AbisTrainingService courseService;
	
	Course courseFound;
	
	Person loggedInperson;
	@Autowired
	CourseRepository courseRepository;
		
	@GetMapping("/")
	public String login(Model model){
		Login l = new Login();
		model.addAttribute("login",l);
		return "login";
	}
	
	@PostMapping("/")
	public String welcome(Model model, @ModelAttribute("login") Login login) {
		loggedInperson =trainingService.findPerson(login.getEmail(),login.getPassword());
		return "redirect:/welcome";
	}
	
	
	@GetMapping("/welcome")
	public String showWelcome(Model model){
		model.addAttribute("person", loggedInperson);
		return "welcome";
	}
	
	@GetMapping("/logout")
	public String logout(){
		return "redirect:/";
	}

	@GetMapping("/coursesearch")
	public String courseSearch(Model model){
		model.addAttribute("person", loggedInperson);
		model.addAttribute("courseById",new Course());
		model.addAttribute("courseByTitle",new Course());
		return "coursesearch";
	}
	
	@GetMapping("/searchallcourse")
	public String showAllCourse(Model model){
		model.addAttribute("person", loggedInperson);
		model.addAttribute("courselist",courseRepository.findAllCourses());
		return "searchallcourse";
		
	}

	@GetMapping("/backToCourseSearch")
	public String backToCourseSearch(){
		return "redirect:/coursesearch";
	}	
	
	@GetMapping("/showcourse")
	public String showCourse(Model model) { 
	 	model.addAttribute("course",courseFound);
		return "showcourse";
	}
	
	@PostMapping("/findcoursebyid")
	public String findCourseById(Course courseById){
		int courseId = Integer.parseInt(courseById.getCourseId());
		Course courseFound = trainingService.getCourseService().findCourse(courseId);	
		return "redirect:/showcourse";
	}
	
	@PostMapping("/findcoursebytitle")
	public String findCourseByTitle(Course courseByTitle){
		Course courseFound = trainingService.getCourseService().findCourse(courseByTitle.getShortTitle());	
		return "redirect:/showcourse";
	}
	
	@GetMapping("/backToWelcome")
	public String backToWelcome(Model model){
		model.addAttribute("person", loggedInperson);
		return "redirect:/welcome";
	}
	
	
	
	
}
