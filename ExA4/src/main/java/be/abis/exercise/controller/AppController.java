package be.abis.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@Controller
public class AppController {
	
	@Autowired
	TrainingService trainingService;
	
	@GetMapping("/")
	public String printCourse(Model model){
		String title = trainingService.getCourseService().findCourse(7900).getShortTitle();
		model.addAttribute("coursetitle", title);
		Person p = trainingService.findPerson(3);
		model.addAttribute("person",p);
		return "course";
	}
}
