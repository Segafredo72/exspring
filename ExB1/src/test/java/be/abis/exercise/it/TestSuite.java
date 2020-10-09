package be.abis.exercise.it;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({TestCourseRepository.class,TestCourseService.class, TestPersonRepository.class, TestWebPages.class})
public class TestSuite {
	public TestSuite() {
		
	}
}
