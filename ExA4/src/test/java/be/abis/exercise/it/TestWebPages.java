package be.abis.exercise.it;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import be.abis.exercise.controller.AppController;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.TrainingService;

@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
public class TestWebPages {

	@Autowired private MockMvc mockMvc;
	@MockBean TrainingService trainingService;
	@MockBean CourseService courseService;
	
	@MockBean Person person;
	
 	@Test
	public void returnedPageContainsCorrectTitle() throws Exception {
		when(trainingService.getCourseService()).thenReturn(courseService);
		
		when(trainingService.findPerson(3)).thenReturn(person);
		//when(person.getCompany()).thenReturn(company);

		when(courseService.findCourse(anyInt())).thenReturn(new Course("7900","Maven","Maven",3,500.0));
		mockMvc.perform(get("/"))
		       .andDo(print())
		       .andExpect(status().isOk())
		       .andExpect(content().string(containsString("Maven")));
	} 
	
}
