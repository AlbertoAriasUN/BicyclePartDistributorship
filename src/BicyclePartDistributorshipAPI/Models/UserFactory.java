package BicyclePartDistributorshipAPI.Models;

import BicyclePartDistributorshipAPI.Models.User.UserType;
import Database.IDatabaseModelFactory;

public class UserFactory implements IDatabaseModelFactory {

	@Override
	public Object create(String csv) {
		String[] values = csv.split(",");
		User user = new User();
		user.setFirstName(values[0]);
		user.setLastName(values[1]);
		user.setUsername(values[2]);
		user.setPasswordHash(values[3]);
		user.setPasswordSalt(values[4]);
		user.setEmail(values[5]);
		switch(values[6]) {
			case "SYSADMIN":
				user.setUserType(UserType.SYSADMIN);
				break;
			case "OFFICE_MANAGER":
				user.setUserType(UserType.OFFICE_MANAGER);
				break;
			case "WAREHOUSE_MANAGER":
				user.setUserType(UserType.WAREHOUSE_MANAGER);
				break;
			case "SALES_ASSOCIATE":
				user.setUserType(UserType.SALES_ASSOCIATE);
				break;
			default:
				user.setUserType(null);
				break;
		}
		return user;
	}

}
