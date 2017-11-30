package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Database Generic
 * @author maneiro
 *
 * @param <E> Model for database row
 */
public class Database<E extends IDatabaseModel> {

	/**
	 * Database values w/ primary key
	 */
	private Map<Object, E> values;

	/**
	 * Path to DB file
	 */
	private final String dbFilename;

	private final IDatabaseModelFactory modelFactory;

	/**
     * Constructs empty Database
     * @param modelFactory
     */
    public Database(IDatabaseModelFactory modelFactory) {
        this.dbFilename = null;
        this.modelFactory = modelFactory;
        values = new HashMap<>();
    }

    /**
     * Constructs Database
     * @param dbFilename Path to DB file
     * @param modelFactory
     * @throws IOException
     */
    public Database(String dbFilename, IDatabaseModelFactory modelFactory) throws IOException {
        this.dbFilename = dbFilename;
        this.modelFactory = modelFactory;
        values = readDB();
    }

    /**
     * Adds data from new DB file
     * @param dbFilename New DB file
     * @throws IOException Exception in reading file
     */
    public void append(String dbFilename) throws IOException {
        ArrayList<E> valuesList = new ArrayList<>();
        Map<Object, E> values = readDB(dbFilename);

        values.forEach((v,k) -> valuesList.add(k));
        for(E value : valuesList) {
            this.values.put(value.getPrimaryKey(), value);
        }

        writeAll();
    }

    /**
     * Sets new value for a given row
     * @param value New value for row
     * @throws IOException Exception in writing to DB file
     */
    public void setValue(E value) throws IOException {
        values.put(value.getPrimaryKey(), value);
        writeAll();
    }

    /**
     * Synonym for setValue
     * @param value New value to be added
     * @throws IOException Error in writing to DB file
     */
    public void addValue(E value) throws IOException {
        setValue(value);
    }

    public void remove(Object primaryKey) throws IOException {
    	values.remove(primaryKey);
    	writeAll();
    }

    /**
     * Gets all rows
     * @return HashMap of all rows
     * @throws IOException
     */
    public Map<Object, E> getValues() throws IOException {
        values = readDB();
        return values;
    }

    public ArrayList<E> getValuesList() throws IOException {
    	values = readDB();
    	ArrayList<E> list = new ArrayList<>();
    	values.forEach((k,v) -> list.add(v));
    	return list;
    }

    /**
     * Gets a specific part listing
     * @param partNumber Part number of part listing to get
     * @return Corresponding part listing
     */
    public E getValue(Object key) throws IOException {
        values = readDB();
        return values.get(key);
    }

    public E getValueEquals(String name, Object value) throws ClassNotFoundException, IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        for(E _value : getValuesList()) {
            Field field = _value.getClass().getDeclaredField(name);
            field.setAccessible(true);
    		if(field.get(_value).equals(value)) {
    			return _value;
    		}
    	}
    	return null;
    }

    /**
     * Gets filename of PartWarehouses's DB
     * @return filename of DB
     */
    public String getDBFilename() {
        return dbFilename;
    }

	/**
     * Reads DB file to HashMap
     * @return HashMap of values in DB file
     * @throws IOException Exception in reading file
     */
    @SuppressWarnings("unchecked")
	private Map<Object, E> readDB() throws IOException {
        Map<Object, E> values = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFilename))) {
            String line;
            while((line = reader.readLine()) != null){
            	E value = (E) modelFactory.create(line);
                values.put(value.getPrimaryKey(), value);
            }
            return values;
        }
        catch(FileNotFoundException e) {
            return values;
        }
    }

    /**
     * Reads different DB file to HashMap
     * @param dbFilename File to read
     * @return HashMap of values in DB file
     * @throws IOException Exception in reading file
     */
	@SuppressWarnings("unchecked")
	private Map<Object, E> readDB(String dbFilename) throws IOException {
        Map<Object, E> values = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFilename))) {
            String line;
            while((line = reader.readLine()) != null){
            	E value = (E) modelFactory.create(line);
                values.put(value.getPrimaryKey(), value);
            }
        }
        return values;
    }

	/**
     * Writes entire parts HashMap to DB file
     * @throws IOException
     */
    private void writeAll() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dbFilename))) {
            ArrayList<String> serializedValues = new ArrayList<>();
            values.forEach((k,v) -> serializedValues.add(v.toString()));
            for(String s : serializedValues){
                writer.write(s + "\n");
            }
        }
    }
}
