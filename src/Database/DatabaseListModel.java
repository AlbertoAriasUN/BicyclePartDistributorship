package Database;

import Database.IDatabaseModel;

public class DatabaseListModel implements IDatabaseModel{

	private String databaseFilePath;

	public DatabaseListModel(String datbaseFilePath) {
		this.databaseFilePath = datbaseFilePath;
	}

	public String getDatabaseFilePath() {
		return databaseFilePath;
	}

	public void setDatabaseFilePath(String databaseFilePath) {
		this.databaseFilePath = databaseFilePath;
	}

	@Override
	public Object getPrimaryKey() {
		return databaseFilePath;
	}
}
