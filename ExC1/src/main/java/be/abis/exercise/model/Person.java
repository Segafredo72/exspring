package be.abis.exercise.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Person {
	
	private int personId;
	
	@NotBlank ( message="Please fill in First Name")
	private String firstName;
	private String lastName;
	private int age;
	
	@NotBlank ( message="Please fill in e-mail")
	private String emailAddress;
	
	@NotBlank ( message="Please fill in password")
	private String password;
	private String language;
	
	@Valid
	private Company company;
	
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Person with id " + personId + ", " + firstName + " "+ lastName + ", works for " +company.getName() + " in " + company.getAddress().getTown();
	}

	

}
