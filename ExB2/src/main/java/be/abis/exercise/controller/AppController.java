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
	
	Course course;
	
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
	public String coursesearch(Model model){
		model.addAttribute("person", loggedInperson);
		return "coursesearch";
	}
	
	@GetMapping("/searchallcourse")
	public String showallcourse(Model model){
		model.addAttribute("person", loggedInperson);
		model.addAttribute("courselist",courseRepository.findAllCourses());
		return "searchallcourse";
		
	}

	@GetMapping("/showcoursebyid")
	public String showcoursebyid(Model model , Course c) { 
		model.addAttribute("person", loggedInperson);
   		model.addAttribute("course",c);
		return "showcoursebyid";
		
	}
		
	@GetMapping("/findcoursebyid")
	public String getcoursebyid(Model model){
		String searchedCourseId = "";
		model.addAttribute("person", loggedInperson);
		model.addAttribute("searchedCourseId",searchedCourseId);
		return "findcoursebyid";
	}
	
	@PostMapping("/findcoursebyid")
	public String submitcoursebyid(Model model,  @ModelAttribute("findcoursebyid") String searchedCourseId) {
		model.addAttribute("person", loggedInperson);
		model.addAttribute("course", courseRepository.findCourse(searchedCourseId));
		return "redirect:/showcoursebyid";
		
	}
	
}
