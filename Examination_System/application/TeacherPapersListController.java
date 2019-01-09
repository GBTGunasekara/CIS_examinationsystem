package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.util.Callback;
import javafx.util.Duration;
import tables.PaperListTable;
import tables.ViewClassTable;


public class TeacherPapersListController implements Initializable{

	@FXML 
	private Button Closebttn2, Minimizebttn2, ViewQuestionsbtn;
	
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;

	@FXML 
	private TableView<TeacherPaperListTable> viewPaperListTbl;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> PaperIDcol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> PaperPasswordcol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> ClassIDcol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> NoQecol;
	//@FXML 
	//private TableColumn<TeacherPaperListTable, String> NoAnscol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> createdatecol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> releaseDateCol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> terminateDateCol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> stCountCol;
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
	
	
	String paID = null;
	String numQe = null;
	@FXML
	private void SelectRow() {
		
		TeacherPaperListTable paperList = viewPaperListTbl.getSelectionModel().getSelectedItem();
		if(paperList != null)
		{
			this.paID = paperList.getPaperID();
			this.numQe = paperList.getNoQuestions();
		}
		else
		{
			System.out.println("select valid row");
		}
	}
	@FXML
	private void MovetoViewQuestions(MouseEvent event) throws NotBoundException, IOException
	{
		
		
	
		if(paID != null && numQe != null)
		{
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/TeacherViewQuestionsGUI.fxml"));
			Parent root = loader.load();
			stage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
			
			TeacherViewQuestionsController taqc = loader.getController();
			taqc.setTeacherViewPaperDetails(paID,numQe); //pass this values to next GUI
			
			Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
			stage2.close();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Select a Row");
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


	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		liveDateTime();
		
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		
		//Reference (You Tube video)
		// B2 Tech (2017).JavaFx Database Operations - Part 8 - Display the DB values in TableView. [Video] Available at: https://www.youtube.com/watch?v=L8iuBXl-F8U [Accessed Day : 17-12-2018].
		
		PaperIDcol.setCellValueFactory(cellData -> cellData.getValue().getPID());
		PaperPasswordcol.setCellValueFactory(cellData -> cellData.getValue().getPwd());
		ClassIDcol.setCellValueFactory(cellData -> cellData.getValue().getCID());
		NoQecol.setCellValueFactory(cellData -> cellData.getValue().getNoQ());
		//NoAnscol.setCellValueFactory(cellData -> cellData.getValue().getNoAns());
		createdatecol.setCellValueFactory(cellData -> cellData.getValue().getCtDate());
		releaseDateCol.setCellValueFactory(cellData -> cellData.getValue().getReDateTime());
		terminateDateCol.setCellValueFactory(cellData -> cellData.getValue().getTeDateTime());
		stCountCol.setCellValueFactory(cellData -> cellData.getValue().getstCount());
		
		
		try {
			String teacherID = "TID123";
			ObservableList<TeacherPaperListTable> plist = TeacherPapersListFunction.selcetPaperList1(teacherID);
			viewPaperListTbl.setItems(plist);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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

}
