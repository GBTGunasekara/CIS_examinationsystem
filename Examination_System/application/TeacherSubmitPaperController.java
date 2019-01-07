package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTimePicker;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TeacherSubmitPaperController implements Initializable{
	
	@FXML
	private Button Closebttn, Minimizebttn;
	@FXML
	private Button Submitbtn;
	
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label teacherIDlbl;
	@FXML
	private Label classIDlbl;
	@FXML
	private Label paperIDlbl;
	@FXML
	private Label noQusetionlbl;
	@FXML
	private Label noAnswerlbl;
	@FXML
	private Label paperPasswordlbl;
	@FXML
	private JFXDatePicker ReleaseDatedp;
	@FXML
	private JFXDatePicker ReleaseTimedp;
	@FXML
	private JFXDatePicker TerminateDatedp;
	@FXML
	private JFXDatePicker TerminateTimedp;
	
	
	
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
	
	public void  paperDetails(String techerID, String classID, String paperID, String noQuestions, String noAnswers ) throws MalformedURLException, RemoteException, NotBoundException
	{
		  teacherIDlbl.setText(techerID);
		 classIDlbl.setText(classID);
		 paperIDlbl.setText(paperID);
		 noQusetionlbl.setText(noQuestions);
		noAnswerlbl.setText(noAnswers);
		
		TeacherSubmitPaperFunctionInterface  passwordGen = (TeacherSubmitPaperFunctionInterface) 
				Naming.lookup("rmi://localhost:1099/TeacherSubmitPaper");
		
		paperPasswordlbl.setText(passwordGen.GeneratePassword()); //set the automatically generated password to label
	}
	
	
	@FXML
	private void SubmitExamPaper(MouseEvent event) throws RemoteException, MalformedURLException, NotBoundException 
	{
		
		String paperID =  paperIDlbl.getText();
		String paperPassword =  paperPasswordlbl.getText();
		String classID =  classIDlbl.getText(); 
		String teacherID=  teacherIDlbl.getText();
		LocalDate ReleaseDate = ReleaseDatedp.getValue();
		LocalTime ReleaseTime = ReleaseTimedp.getTime();
		LocalDate TerminateDate = TerminateDatedp.getValue();
		LocalTime TerminateTime = TerminateTimedp.getTime();
		
		//TeacherSubmitPaperFunctionInterface  submit = (TeacherSubmitPaperFunctionInterface) 
			//	Naming.lookup("rmi://localhost:1099/TeacherSubmitPaper");
		
		//create thread
		Service<Void>src=new Service<Void>() {
			protected Task<Void>createTask(){
				return new Task<Void>() {
					protected Void call()throws Exception{
						TeacherSubmitPaperFunction tspf = new TeacherSubmitPaperFunction();  
						tspf.submitPaper(paperID, paperPassword,ReleaseDate,ReleaseTime,TerminateDate,TerminateTime); //called submitPaper method here and pass these values
						tspf.generateEmail(paperID, paperPassword, classID, teacherID, String.valueOf(ReleaseDate +" "+ ReleaseTime ), String.valueOf(TerminateDate +" " + TerminateTime));
						return null;
					}
				};
			}
		};
	    src.start();
		
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		
	}
	
	
}
