package Main.Models;

import BicyclePartDistributorshipAPI.Models.User.UserType;

public class UserManagementTableRow {
	private String username;
	private String email;
	private UserType userType;
	
	public UserManagementTableRow() {
		this.username = "";
		this.email = "";
		this.userType = null;
	}

	public UserManagementTableRow(String username, String email, UserType userType) {
		this.username = username;
		this.email = email;
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
