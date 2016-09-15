package com.sgannu.ivalidator.example;

import com.sgannu.ivalidator.Validate;

public class SampleWebForm {
	
	@Validate(with=NameValidator.class)
	private String firstName;

	@Validate(with=NameValidator.class)
	private String lastName;

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

}
