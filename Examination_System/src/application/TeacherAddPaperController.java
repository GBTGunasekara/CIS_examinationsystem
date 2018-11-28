package application;

import java.io.FileNotFoundException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TeacherAddPaperController implements Initializable{
	
	@FXML 
	private Button Closebttn2, Minimizebttn2;
	
	@FXML
	private Button createbtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private JFXTextField teacherIDtxt;
	@FXML
	private JFXTextField classIDtxt;
	@FXML
	private JFXTextField paperIDtxt;
	@FXML
	private JFXTextField noQusetiontxt;
	@FXML
	private JFXTextField noAnswertxt;
	@FXML
	private JFXTextField subjcetNametxt;
	
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource(link));
		
		Parent root = loader.load();
		
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
		TeacherAddQuestionController taqc = loader.getController();
		taqc.setPaperDetails(paperIDtxt.getText(), Integer.parseInt(noQusetiontxt.getText())); //pass this values to next GUI
	}
	
	@FXML
	private void MovetoAddQuestions(MouseEvent event) throws Exception
	{
		CreatePaper();
		
		String link = "/application/TeacherAddQuestionsGUI.fxml";
		fxmlLoader(link);
		
		
		
	}
	
	@FXML
	private void CreatePaper() throws RemoteException 
	{
		
		String techerID = teacherIDtxt.getText();
		String classID = classIDtxt.getText();
		String paperID = paperIDtxt.getText();
		String subjcetName = subjcetNametxt.getText();
		int noQuestions = Integer.parseInt(noQusetiontxt.getText());
		int noAnswers = Integer.parseInt(noAnswertxt.getText());

	    Date currentdatetime = new Date();
	    	    
		TeacherAddPaperFunction tap1 = new TeacherAddPaperFunction();
		tap1.createPaper(techerID,classID,paperID, currentdatetime,noQuestions,noAnswers);
		
	}
	
	/*public static int noQuestions = 0;
	
	public int passQuestionCount () //pass question counts to add question panel
	{
		noQuestions = Integer.parseInt(noQusetiontxt.getText());
		return noQuestions;
	}*/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		Drawer.toBack();
		
		TeacherAddPaperFunction adpf = new TeacherAddPaperFunction(); //paperID generate and show it on text field when load this GUI
		paperIDtxt.setText(adpf.paperIDgenerate ());
		
		
	}
	}
