package be.abis.exercise.it;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestTrainingService {

	@Autowired
	TrainingService trainingService;
	
	@Test
	public void person3isBob() {
		Person found = trainingService.findPerson(3);
		assertEquals("Bob",found.getFirstName());
		
	}

	
}
