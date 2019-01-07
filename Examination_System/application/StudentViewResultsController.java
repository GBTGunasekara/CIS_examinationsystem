package application;

import java.net.URL;
import java.rmi.RemoteException;
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

public class StudentViewResultsController implements Initializable{

	@FXML
	private Button Closebttn3, Minimizebttn3, savebtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML 
	private TableView<StudentViewResultsTable> viewResultTbl;
	@FXML 
	private TableColumn<StudentViewResultsTable, String> PaperIDcol;
	@FXML 
	private TableColumn<StudentViewResultsTable, String> TeacherID;
	@FXML 
	private TableColumn<StudentViewResultsTable, String> ClassIDcol;
	@FXML 
	private TableColumn<StudentViewResultsTable, String> NoQecol;
	@FXML 
	private TableColumn<StudentViewResultsTable, String> MarksCol;
	@FXML 
	private TableColumn<StudentViewResultsTable, String> ansdatecol;
	
	
	
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
		
		

		//Reference (You Tube video)
		// B2 Tech (2017).JavaFx Database Operations - Part 8 - Display the DB values in TableView. [Video] Available at: https://www.youtube.com/watch?v=L8iuBXl-F8U [Accessed Day : 17-12-2018].
		
		PaperIDcol.setCellValueFactory(cellData -> cellData.getValue().getPID());
		TeacherID.setCellValueFactory(cellData -> cellData.getValue().getTID());
		ClassIDcol.setCellValueFactory(cellData -> cellData.getValue().getCID());
		NoQecol.setCellValueFactory(cellData -> cellData.getValue().getNoQ());
		MarksCol.setCellValueFactory(cellData -> cellData.getValue().getMk());
		ansdatecol.setCellValueFactory(cellData -> cellData.getValue().getAnsTime());


		try {
			String studentID = "SID123";
			ObservableList<StudentViewResultsTable> rlist = StudentViewResultsFunction.selcetStudentResultsList(studentID);
			viewResultTbl.setItems(rlist);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
