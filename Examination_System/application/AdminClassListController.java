package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class AdminClassListController implements Initializable {
	@FXML
	private Button Closebttn4; 
	@FXML
	private Button Minimizebttn4; 
	@FXML
	private Button ViewClassbtn;
	@FXML
	private Button DeleteClassbtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	
	@FXML 
	private TableView<AdminClassListTable> viewClassTbl;
	@FXML 
	private TableColumn<AdminClassListTable, Integer> col_ClassID;
	@FXML 
	private TableColumn<AdminClassListTable, Integer> col_TeacherID;
	@FXML 
	private TableColumn<AdminClassListTable, String> col_ClassName;
	@FXML 
	private TableColumn<AdminClassListTable, String> col_SubjectName;
	@FXML 
	private TableColumn<AdminClassListTable, Integer> col_Grade;
	@FXML
	private TableColumn<AdminClassListTable, String> col_Location;
	@FXML
	private Label UIDlbl;
	@FXML
	private Label systemTimelbl;
	
	//set the logged user id on the GUI
	public void setUserID () throws IOException, ClassNotFoundException
	{
		FileInputStream fis = new  FileInputStream("userfile.txt");		//create the object of give file
		ObjectInputStream ois = new ObjectInputStream(fis);
		UserDetails uobj  = (UserDetails) ois.readObject();				//read file object
		UIDlbl.setText(uobj.userID);									//set the current logged user's id on UIDlbl label   
		ois.close(); 													//close ObjectInputStream
		fis.close();													//close FileInputStream
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
	
	public void fxmlLoader(String link) throws Exception
	{
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource(link));
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void MovetoAdminViewClass(MouseEvent event) throws Exception
	{
		String link = "/application/AdminViewClassGUI.fxml";
		fxmlLoader(link);
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		liveDateTime ();
		AdminDrawerController ad1 = new AdminDrawerController();
		ad1.AdminDrawer(Hamburger, Drawer);
		
		col_ClassID.setCellValueFactory(cellData -> cellData.getValue().getClassID().asObject());
		col_TeacherID.setCellValueFactory(cellData -> cellData.getValue().getTeacherID().asObject());
		col_ClassName.setCellValueFactory(cellData -> cellData.getValue().getClassName());
		col_SubjectName.setCellValueFactory(cellData -> cellData.getValue().getSubjectName());
		col_Grade.setCellValueFactory(cellData -> cellData.getValue().getGrade().asObject());
		col_Location.setCellValueFactory(cellData -> cellData.getValue().getLocation());

		try {
			try {
				String teacherID = "TID123";
				ObservableList<AdminClassListTable> ClassList = AdminClassListFunction.SelectClassList(teacherID);
				viewClassTbl.setItems(ClassList);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			setUserID ();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	int cid = 0;
	@FXML
	private void SelectRow() {						//Code referred from https://www.youtube.com/watch?v=3SJmlQQvxIs
		ObservableList<AdminClassListTable> ClList;
		ClList=viewClassTbl.getSelectionModel().getSelectedItems();
		cid = ClList.get(0).getClID();
	}
	@FXML
	private void DeleteClass() {
		
		if(cid == 0)
			JOptionPane.showMessageDialog(null, "Select Class Row");
		else
		{
			AdminClassListFunction aclf = new AdminClassListFunction();
			aclf.DeleteRow(cid);
			
			col_ClassID.setCellValueFactory(cellData -> cellData.getValue().getClassID().asObject());
			col_TeacherID.setCellValueFactory(cellData -> cellData.getValue().getTeacherID().asObject());
			col_ClassName.setCellValueFactory(cellData -> cellData.getValue().getClassName());
			col_SubjectName.setCellValueFactory(cellData -> cellData.getValue().getSubjectName());
			col_Grade.setCellValueFactory(cellData -> cellData.getValue().getGrade().asObject());
			col_Location.setCellValueFactory(cellData -> cellData.getValue().getLocation());

			try {
				try {
					String teacherID = "TID123";
					ObservableList<AdminClassListTable> ClassList = AdminClassListFunction.SelectClassList(teacherID);
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
	// show live system time on the window 
			public void liveDateTime () 
			{
				//reference - https://stackoverflow.com/questions/42383857/javafx-live-time-and-date 
						Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
					        int second = LocalDateTime.now().getSecond();
					        int minute = LocalDateTime.now().getMinute();
					        int hour = LocalDateTime.now().getHour();
					        int day = LocalDateTime.now().getDayOfMonth();
					        int month = LocalDateTime.now().getMonthValue();
					        int year = LocalDateTime.now().getYear();
					        
					        systemTimelbl.setText(hour +":"+ minute + ":" + second+ "    "+ day + "/"+ month + "/" +year);
					    }),
					         new KeyFrame(Duration.seconds(1))
					    );
					    clock.setCycleCount(Animation.INDEFINITE);
					    clock.play();
			}
	
}
