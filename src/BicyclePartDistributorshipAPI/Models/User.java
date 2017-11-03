package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModel;

public class User implements IDatabaseModel {

	/**
	 * Unique username
	 */
	private String username;
	
	/**
	 * Password encrypted with password hash
	 */
	private String passwordHash;

	/**
	 * Salt to be used in password hash
	 */
	private String passwordSalt;

	private String firstName;
	
	private String lastName;
	
	/**
	 * Email address for user
	 */
	private String email;

	/**
	 * Type of user account
	 */
	private UserType userType;

	public enum UserType {
		SYSADMIN, OFFICE_MANAGER, WAREHOUSE_MANAGER, SALES_ASSOCIATE
	}

	/**
	 * Constructs empty User
	 */
	public User() {
		username = "";
		passwordHash = "";
		passwordSalt = "";
		email = "";
		userType = null;
	}

	public User(String username, String password, String email, UserType userType) {
		this.username = username;
		this.passwordHash = password;
		this.email = email;
		this.userType = userType;
	}

	public String getFirstname() {
		return firstName;
	}
	
	public String getLastname() {
		return lastName;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public String getEmail() {
		return email;
	}

	public UserType getType() {
		return userType;
	}

	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastname(String lastName) {
		this.lastName = lastName;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public Object getPrimaryKey() {
		return username;
	}

	@Override
	public String toString() {
		return firstName + "," + lastName + "," + username + "," + passwordHash + "," + 
			   passwordSalt + "," + email + "," + userType.toString();
	}
}
