package Database;

import Database.IDatabaseModelFactory;

public class DatabaseListModelFactory implements IDatabaseModelFactory {

	@Override
	public Object create(String databaseFilePath) {
		return new DatabaseListModel(databaseFilePath);
	}

}
