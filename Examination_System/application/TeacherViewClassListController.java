package application;


import java.net.URL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tables.ViewClassListTable;

public class TeacherViewClassListController {
	@FXML
	private Button Closebttn, Minimizebttn;
	@FXML 
	private TableView<ViewClassListTable> viewClassListTbl;
	@FXML
	private TableColumn<ViewClassListTable, String> col_clID;
	@FXML
	private TableColumn<ViewClassListTable, String> col_clName;
	@FXML
	private TableColumn<ViewClassListTable, String> col_grade;
	@FXML
	private TableColumn<ViewClassListTable, String> col_subName;
	@FXML
	private TableColumn<ViewClassListTable, String> col_location;
	@FXML
	private TableColumn<ViewClassListTable, String> col_noStudents;
	
	
	
	
	
	ResultSet rs = null;
	

	public void tableload() {
		//fill table
		ObservableList<ViewClassListTable> oblist = FXCollections.observableArrayList();
		try {
			
			Connection con = DBconnection.Connect();
			rs = con.createStatement().executeQuery("SELECT classID, className, grade, subjectName, location, NoStudents FROM class");
			
			while (rs.next()) {
				oblist.add(new ViewClassListTable(rs.getString("classID"), rs.getString("className"), rs.getString("grade"),
						rs.getString("subjectName"), rs.getString("location"), rs.getString("NoStudents") ));
			}
			
			
		
		}catch(SQLException ex) {
			System.out.println(ex);
	    	JOptionPane.showMessageDialog(null, "error\n" + ex);
		}
		
		//table columns
		col_clID.setCellValueFactory(new PropertyValueFactory<>("clID"));
		col_clName.setCellValueFactory(new PropertyValueFactory<>("clName"));
		col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
		col_subName.setCellValueFactory(new PropertyValueFactory<>("subName"));
		col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
		col_noStudents.setCellValueFactory(new PropertyValueFactory<>("noStudents"));
		
		viewClassListTbl.setItems(oblist);
	}
	
	
	@FXML
	private void handleClose(MouseEvent event)
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void handleMinimize(MouseEvent event)
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		tableload();
	}
	
}
