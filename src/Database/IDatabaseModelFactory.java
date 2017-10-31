package Database;

public interface IDatabaseModelFactory {
	public abstract Object create(String csv);
}
