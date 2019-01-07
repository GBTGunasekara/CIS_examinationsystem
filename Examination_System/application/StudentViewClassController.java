package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentViewClassController implements Initializable{

	@FXML
	private Button Closebttn3, Minimizebttn3, savebtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML 
	private TableView<TeacherViewClassTable> viewClassTbl;
	@FXML 
	private TableColumn<TeacherViewClassTable, Integer> col_ClassID;
	@FXML 
	private TableColumn<TeacherViewClassTable, Integer> col_TeacherID;
	@FXML 
	private TableColumn<TeacherViewClassTable, String> col_ClassName;
	@FXML 
	private TableColumn<TeacherViewClassTable, String> col_SubjectName;
	@FXML 
	private TableColumn<TeacherViewClassTable, Integer> col_Grade;
	@FXML
	private TableColumn<TeacherViewClassTable, String> col_Location;
	
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StudentDrawerController ad1 = new StudentDrawerController();
		ad1.StudentDrawer(Hamburger, Drawer);
		
		col_ClassID.setCellValueFactory(cellData -> cellData.getValue().getClassID().asObject());
		col_TeacherID.setCellValueFactory(cellData -> cellData.getValue().getTeacherID().asObject());
		col_ClassName.setCellValueFactory(cellData -> cellData.getValue().getClassName());
		col_SubjectName.setCellValueFactory(cellData -> cellData.getValue().getSubjectName());
		col_Grade.setCellValueFactory(cellData -> cellData.getValue().getGrade().asObject());
		col_Location.setCellValueFactory(cellData -> cellData.getValue().getLocation());

		try {
			try {
				String teacherID = "TID123";
				ObservableList<TeacherViewClassTable> ClassList = TeacherViewClassFunction.SelectClassList(teacherID);
				viewClassTbl.setItems(ClassList);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
