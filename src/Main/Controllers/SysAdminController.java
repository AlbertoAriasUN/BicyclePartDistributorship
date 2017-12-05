package Main.Controllers;
import java.io.IOException;
import java.util.ArrayList;

import BicyclePartDistributorshipAPI.Models.SalesVan;
import BicyclePartDistributorshipAPI.Models.User;
import BicyclePartDistributorshipAPI.Models.User.UserType;
import Main.APICaller;
import Main.Models.SalesVanAssignmentTableRow;
import Main.Models.UserManagementTableRow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;

public class SysAdminController extends FXMLController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;
    
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    private ComboBox<String> userTypeComboBox;
    
    @FXML
    private TableView<UserManagementTableRow> userManagementTable;

    @FXML
    private TableView<SalesVanAssignmentTableRow> salesVanAssignmentTable;
    
    @FXML
    private TableColumn<SalesVanAssignmentTableRow, String> assignment_salesVanColumn;
    
    @FXML  	
    void initialize() {
    	ArrayList<String> userTypeSelections = new ArrayList<>();
    	userTypeSelections.add("System Administrator");
    	userTypeSelections.add("Office Manager");
    	userTypeSelections.add("Warehouse Manager");
    	userTypeSelections.add("Sales Associate");
    	userTypeComboBox.getItems().setAll(userTypeSelections);
    	
    	assignment_populateTable();
    	assignment_salesVanColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    	assignment_salesVanColumn.setOnEditCommit(event -> assignment_salesVanColumnChanged(event));
    	
    	try {
			management_populateTable();
		} catch (IOException e) {
			showErrorDialog("Error: " + e.getMessage());
		}
    }
    
    @FXML
    void registerUser(ActionEvent event) throws IOException {
    	String firstName = firstNameField.getText();
    	String lastName = lastNameField.getText();
    	String username = usernameField.getText();
    	String email = emailField.getText();
    	String password = passwordField.getText();
    	UserType userType = null;
    	switch(userTypeComboBox.getValue().toLowerCase()) {
    		case "sysadmin":
    			userType = UserType.SYSADMIN;
    			break;
    		case "office manager":
    			userType = UserType.OFFICE_MANAGER;
    			break;
    		case "warehouse manager": 
    			userType = UserType.WAREHOUSE_MANAGER;
    			break;
    		case "sales associate":
    			userType = UserType.SALES_ASSOCIATE;
    			break;
    		default:
    			userType = null;
    			break;
    	}
    	APICaller.getUserController().registerUser(firstName, lastName, username, password, email, userType);
    	showInfoDialog("User registered");
    }
    
    @FXML
    void deleteUser(ActionEvent event) {
    	UserManagementTableRow user = userManagementTable.getSelectionModel().getSelectedItem();
    	try {
			APICaller.getUserController().deleteUser(user.getUsername());
			management_populateTable();
		} catch (IOException e) {
			showErrorDialog("Error: " + e.getMessage());
		}
    }

    private void management_populateTable() throws IOException {
    	ArrayList<User> users = APICaller.getUserController().getUsers();
    	ArrayList<UserManagementTableRow> rows = new ArrayList<>();
    	for(User user : users) {
    		UserManagementTableRow row = new UserManagementTableRow();
    		row.setUsername(user.getUsername());
    		row.setEmail(user.getEmail());
    		row.setUserType(user.getUserType());
    		rows.add(row);
    	}
    	userManagementTable.getItems().setAll(rows);
    }
    
    private void assignment_populateTable() {
    	ArrayList<SalesVanAssignmentTableRow> rows = new ArrayList<>();
    	try {
    		ArrayList<User> salesAssociates = APICaller.getUserController().getSalesAssociates();
        	for(User associate : salesAssociates) {
        		SalesVanAssignmentTableRow assignment = new SalesVanAssignmentTableRow();
        		assignment.setSalesAssociate(associate.getUsername());
        		SalesVan van = APICaller.getSalesVanController().findForUser(associate.getUsername());
        		if(van != null) {
        			assignment.setSalesVan(van.getName());
        		}
        		rows.add(assignment);
        	}
        	salesVanAssignmentTable.getItems().setAll(rows);
    	}
    	catch(Exception e) {
    		showErrorDialog("Could not get Sales Associate Assignments: " + e.getMessage());
    	}
    	
    }
    
    private void assignment_salesVanColumnChanged(CellEditEvent<SalesVanAssignmentTableRow, String> event) {
		String username = event.getRowValue().getSalesAssociate();
		try {
			SalesVan van = APICaller.getSalesVanController().findForUser(username);
	    	if(van == null) {
	    		APICaller.getSalesVanController().addSalesVan(new SalesVan(event.getNewValue(), username));
	    	}
	    	else {
	    		van.setName(event.getNewValue());
	    		APICaller.getSalesVanController().updateSalesVanName(username, event.getNewValue());
	    	}
    		APICaller.getWarehouseListController().addWarehouse(event.getNewValue());
		}
		catch(Exception e) {
			showErrorDialog(e.getMessage());
		}
    }  
}
