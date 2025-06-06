package jp.co.aforce.beans;

import java.io.Serializable;

public class Login implements Serializable {
	private String id;
	private String password;
	private String lastName;
	private String firstName;
	private String address;
	private String email;

	public Login(String id, String password, String lastName, String firstName, String address, String email) {
		this.id = id;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.email = email;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
