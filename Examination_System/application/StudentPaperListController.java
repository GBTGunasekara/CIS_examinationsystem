package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

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

public class StudentPaperListController implements Initializable{
	
	@FXML
	private Button Closebttn3, Minimizebttn3;
	@FXML
	private Button StudentRegbtn, TeacherProfilebtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML 
	private TableView<StudentPaperListTable> viewAnsPaperListTbl;
	@FXML 
	private TableColumn<StudentPaperListTable, String> PaperIDcol;
	@FXML 
	private TableColumn<StudentPaperListTable, String> PaperPasswordcol;
	@FXML 
	private TableColumn<StudentPaperListTable, String> ClassIDcol;
	@FXML 
	private TableColumn<StudentPaperListTable, String> NoQecol;
	@FXML 
	private TableColumn<StudentPaperListTable, String> MarksCol;
	//@FXML 
	//private TableColumn<TeacherPaperListTable, String> NoAnscol;
	@FXML 
	private TableColumn<StudentPaperListTable, String> ansdatecol;
	@FXML 
	private TableColumn<StudentPaperListTable, String> releaseDateCol;
	@FXML 
	private TableColumn<StudentPaperListTable, String> terminateDateCol;
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
			        
			        systemTimelbl.setText(hour +":"+ minute + ":" + second+ "  "+ day + "/"+ month + "/" +year);
			    }),
			         new KeyFrame(Duration.seconds(1))
			    );
			    clock.setCycleCount(Animation.INDEFINITE);
			    clock.play();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StudentDrawerController ad1 = new StudentDrawerController();
		ad1.StudentDrawer(Hamburger, Drawer);
		
		liveDateTime (); 
		try {
			setUserID ();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Reference (You Tube video)
		// B2 Tech (2017).JavaFx Database Operations - Part 8 - Display the DB values in TableView. [Video] Available at: https://www.youtube.com/watch?v=L8iuBXl-F8U [Accessed Day : 17-12-2018].
		
		PaperIDcol.setCellValueFactory(cellData -> cellData.getValue().getPID());
		PaperPasswordcol.setCellValueFactory(cellData -> cellData.getValue().getPwd());
		ClassIDcol.setCellValueFactory(cellData -> cellData.getValue().getCID());
		NoQecol.setCellValueFactory(cellData -> cellData.getValue().getNoQ());
		MarksCol.setCellValueFactory(cellData -> cellData.getValue().getMk());
		//NoAnscol.setCellValueFactory(cellData -> cellData.getValue().getNoAns());
		ansdatecol.setCellValueFactory(cellData -> cellData.getValue().getAnsTime());
		releaseDateCol.setCellValueFactory(cellData -> cellData.getValue().getReDateTime());
		terminateDateCol.setCellValueFactory(cellData -> cellData.getValue().getTeDateTime());

		try {
			String studentID = "SID123";
			ObservableList<StudentPaperListTable> plist = StudentPaperListFunction.selcetAnsPaperList(studentID);
			viewAnsPaperListTbl.setItems(plist);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
