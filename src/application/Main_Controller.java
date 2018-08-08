package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main_Controller implements Initializable {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public Main_Controller() throws SQLException {
		conn = DBConnector.getConnection();
	}
	
	@FXML
	private BorderPane main_pane;

	@FXML
	private Button minimizebtn;

	@FXML
	private Button closebtn;

	@FXML
	private Button menubtn_last_generation;

	@FXML
	private Button menubtn_database;

	@FXML
	private BorderPane Last_Generation_pane;

	@FXML
	private TableView<Generation_Data> table_1;

	@FXML
	private TableColumn Description_Col1;

	@FXML
	private TableColumn Day_Peak_Col1;

	@FXML
	private TableColumn Evening_Peak_Col1;

	@FXML
	private BorderPane Database_pane;

	@FXML
	private DatePicker Filter_using_date;

	@FXML
	private Button Show_btn;

	@FXML
	private TableView<?> table_2;

	@FXML
	private TableColumn<?, ?> Description_Col2;

	@FXML
	private TableColumn<?, ?> Day_Peak_Col2;

	@FXML
	private TableColumn<?, ?> Evening_Peak_Col2;

	@FXML
	private TableColumn<?, ?> Date_Col;

	@FXML
	void Control_btn_Action(ActionEvent event) {
		if (event.getSource() == closebtn) {
			Stage stage = (Stage) closebtn.getScene().getWindow();
			stage.close();
		}
		if (event.getSource() == minimizebtn) {
			Stage stage = (Stage) minimizebtn.getScene().getWindow();
			stage.setIconified(true);
		}
	}

	@FXML
	void Show_Btn_Action(ActionEvent event) {

	}

	@FXML
	void menu_BtnAction(ActionEvent event) {
		if (event.getSource() == menubtn_database) {
			Database_pane.setVisible(true);
			Last_Generation_pane.setVisible(false);
		}
		if (event.getSource() == menubtn_last_generation) {
			Last_Generation_pane.setVisible(true);
			Database_pane.setVisible(false);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
