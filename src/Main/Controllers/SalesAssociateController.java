package Main.Controllers;

import Main.APICaller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

public class SalesAssociateController {

	@FXML
	private Button chooseFile;

	@FXML
	private DatePicker datePicker;

	@FXML
	private TableView<?> dataBaseTable;

	@FXML
	private TableView<?> saleTable;

	@FXML
	private Button sellButton;

	@FXML
	private TextField totalSold;

	@FXML
	private Button invoicePDF;

	@FXML
	private Button invoiceConsole;

	@FXML
	void generateInvoiceConsole(ActionEvent event) {

	}

	@FXML
	void generateInvoicePDF(ActionEvent event)
	{
	}

	@FXML
	void pickDate(ActionEvent event) {
		datePicker.getConverter().toString();

	}

	@FXML
	void sellThings(ActionEvent event) {

	}

	@FXML
	void sortDataBaseTable(ActionEvent event) {

	}

	@FXML
	void sortSaleTable(ActionEvent event) {

	}

	@FXML
	void chooseFileUpdate(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
			Node node = (Node) event.getSource();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select Transfer File");
			File file = fileChooser.showOpenDialog(node.getScene().getWindow());
			if(file != null)
			{
				//APICaller.getWarehouseController().transferParts(file.getPath());
			}

	}

}
