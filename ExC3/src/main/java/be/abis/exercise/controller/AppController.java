package be.abis.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import be.abis.exercise.exception.PersonCanNotBeDeletedException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Login;
import be.abis.exercise.model.Password;
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
	List<Person> personsFound;
	
	Person loggedInPerson;
	Person removedPerson;
	Person addedPerson;
	
	
	@Autowired
	CourseRepository courseRepository;
		
	@GetMapping("/error")
	public String showErrorPage() {
		return "error";
	}
	
	@GetMapping("/")
	public String login(Model model){
		Login l = new Login();
		model.addAttribute("login",l);
		return "login";
	}
	
	@PostMapping("/")
	public String welcome(Model model, @Valid @ModelAttribute("login") Login login, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			bindingResult.reject("login", "This is invalid login details");
			return ("login");
		}
		
		loggedInPerson =trainingService.findPerson(login.getEmail(),login.getPassword());
		
		if (loggedInPerson == null) {
			bindingResult.reject("email", "This is invalid login details");
			bindingResult.reject("password", "This is invalid password");
			return ("login");
		} else {
			return "redirect:/welcome";
		}
	}
	
	
		
	@GetMapping("/welcome")
	public String showWelcome(Model model){
		model.addAttribute("person", loggedInPerson);
		return "welcome";
	}
	
	@GetMapping("/logout")
	public String logout(){
		return "redirect:/";
	}

	@GetMapping("/coursesearch")
	public String courseSearch(Model model){
		model.addAttribute("person", loggedInPerson);
		model.addAttribute("courseById",new Course());
		model.addAttribute("courseByTitle",new Course());
		return "coursesearch";
	}
	
	@GetMapping("/searchallcourse")
	public String showAllCourse(Model model){
		model.addAttribute("person", loggedInPerson);
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
	
	@PostMapping("/findCourseById")
	public String findCourseById(Course courseById){
		int courseId = Integer.parseInt(courseById.getCourseId());
		courseFound = trainingService.getCourseService().findCourse(courseId);	
		return "redirect:/showcourse";
	}
	
	@PostMapping("/findCourseByTitle")
	public String findCourseByTitle(Model model, @Valid @ModelAttribute("course") Course course,BindingResult result){
		Course courseFound = trainingService.getCourseService().findCourse(course.getShortTitle());	
		if (courseFound==null) {
			   result.rejectValue("shortTitle", "","No course found with this title");
			   return "coursesearch";
		}
		return "redirect:/showcourse";
	}
	
	@GetMapping("/backToWelcome")
	public String backToWelcome(Model model){
		model.addAttribute("person", loggedInPerson);
		return "redirect:/welcome";
	}
	
	@GetMapping("/personadmin")
	public String gotopersonadmin(Model model){
		return "personadmin";
	}
	
	@GetMapping("/changepassword")
	public String showChangePwd(Model model) {
		model.addAttribute("pwd",new Password());
		return "changepassword";
	}
	
	@PostMapping("/changepassword")
	public String postNewPassword(Model model, @Valid @ModelAttribute("pwd") Password password, BindingResult bindingResult) {
		try {
			System.out.println("new password: " +password.getPassword1());
			if (bindingResult.hasErrors()) {
				return "changepassword";
			}
			trainingService.changePassword(loggedInPerson, password.getPassword1());
			password.setConfirmMessage("password was changed");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/confirmpasswordchanged";
		
	}
		
	@GetMapping("/personsearch")
	public String searchPersons(Model model){
		model.addAttribute("personById",new Person());
		return "personsearch";
	}	
	
	@GetMapping("/personList")
	public String showAllPersons(){
		personsFound = trainingService.getAllPersons();		
		return "redirect:/showpersons";
	}
	
	@GetMapping("/showpersons")
	public String showPersons(Model model){
		model.addAttribute("persons", personsFound);
		return "showpersons";
	}
	
	@GetMapping("/backToPersonSearch")
	public String backToPersonSearch(){
		return "redirect:/personsearch";
	}
	
	@PostMapping("/findPersonById")
	public String findPersonById(@Valid @ModelAttribute("personById") Person personById, BindingResult bindingResult){
		Person personFound = trainingService.findPerson(personById.getPersonId());	
		if (personFound==null) {
			   bindingResult.reject("personId", "No person found with this id");
			   return "personsearch";
		}
		personsFound = new ArrayList<Person>();
		personsFound.add(personFound);
		return "redirect:/showpersons";
	}
	
	@GetMapping("/removeperson")
	public String removePerson(Model model){
		model.addAttribute("person", new Person());
		return "removeperson";
	}
	
	@PostMapping("/removePersonById")
	public String removePersonById(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) throws PersonCanNotBeDeletedException{
		 int id = person.getPersonId();
	        if (loggedInPerson.getPersonId()==id) {
	        	bindingResult.reject("personId", "You cannot delete yourself");
				 return "removeperson";
	        }
			removedPerson=trainingService.findPerson(id);
			trainingService.deletePerson(id);		
		return "redirect:/confirmpersonremoved";
	}
	
	
	@GetMapping("/addperson")
	public String addPerson(Model model){
		model.addAttribute("person", new Person());
		return "addperson";
	}
	
	@PostMapping("/addPerson")
	public String addPerson(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult){
		try {		
			if (bindingResult.hasErrors()) {
            return ("addperson");
			}
	
			trainingService.addPerson(person);
			addedPerson = trainingService.findPerson(person.getPersonId());
		} catch (IOException e) {
			bindingResult.reject("personId","This person already exists in our system");
			return "addPerson";
		}	
		return "redirect:/confirmpersonadded";
	}
		
	@GetMapping("/confirmpasswordchanged")
	public String showPwdChanged(Model model) {
		model.addAttribute("person", loggedInPerson);
		return "confirmpasswordchanged";
	}
	
	@GetMapping("/confirmpersonadded")
	public String showPersonAdded(Model model) {
		model.addAttribute("person",addedPerson);
		return "confirmpersonadded";

	}
	
	@GetMapping("/confirmpersonremoved")
	public String showPersonRemoved(Model model) {
		model.addAttribute("person",removedPerson);
		return "confirmpersonremoved";
	}

	
}
