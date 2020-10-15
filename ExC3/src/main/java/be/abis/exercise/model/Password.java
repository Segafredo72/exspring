package be.abis.exercise.model;

import javax.validation.constraints.Size;

import be.abis.exercise.annotation.GoodPassword;

@GoodPassword()
public class Password {
	@Size(min=6, message="password has to be minimum 6 characters")
	private String password1;	
	private String confirmPassword;
	private String confirmMessage="";
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getConfirmMessage() {
		return confirmMessage;
	}
	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}
}
