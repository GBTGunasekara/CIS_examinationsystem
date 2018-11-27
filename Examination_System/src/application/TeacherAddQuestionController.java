package application;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TeacherAddQuestionController implements Initializable{
	
	@FXML 
	private Button Closebttn2, Minimizebttn2;
	
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label paperIDlabel;
	@FXML
	private Label quesetionIDlabel;
	@FXML
	private Label answerIDlabel;
	@FXML
	private Label completeQuestionlabel;
	@FXML
	private Label QuestionNoLabel;
	@FXML
	private Label AnswerNoLabel;
	@FXML
	private ComboBox<Character> AnsNoCombo;
	@FXML
	private ComboBox<Integer> QuestionNoCombo;
	@FXML
	private TextArea QuestionTextArea;
	@FXML
	private TextArea AnswerATextArea;
	@FXML
	private TextArea AnswerBTextArea;
	@FXML
	private TextArea AnswerCTextArea;
	@FXML
	private TextArea AnswerDTextArea;
	@FXML 
	private Button  Savebtn;
	@FXML 
	private Button Nextbtn;
	
	
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
	private void MovetoSubmitPaper(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherSubmitPaperGUI.fxml";
		fxmlLoader(link);
		
	}

	public void setPaperID (String paperid, int noQuestion)
	{
		paperIDlabel.setText(paperid); //set created paperid on teacherAddQuestionGUI 
       
		//insert given question numbers to combo box
		int numbers_to_add_max = noQuestion;
		for ( int i = 1; i <= numbers_to_add_max; i++) 
		{
			QuestionNoCombo.getItems().add(new Integer(i));
		}
		QuestionNoCombo.getSelectionModel().selectFirst(); // select 1st index of combo box
		
	
		for ( int i = 65; i <= 69; i++) 
		{
			AnsNoCombo.getItems().add((char)i);
		}
		
		
	}

	
	@FXML
	private void SaveEnterNextQuestion(MouseEvent event) throws RemoteException 
	{
		
		String PaperID = paperIDlabel.getText();
		String QusetionID= quesetionIDlabel.getText();
		
		String Question = QuestionTextArea.getText();
		String AnswerA = AnswerATextArea.getText();
		String AnswerB = AnswerBTextArea.getText();
		String AnswerC= AnswerCTextArea.getText();
		String AnswerD = AnswerDTextArea.getText();
		
		int noQuestions = Integer.parseInt(QuestionNoCombo.getSelectionModel().getSelectedItem().toString());
		String correctAnswer = AnsNoCombo.getSelectionModel().getSelectedItem().toString();

	   
	    	    
		TeacherAddQuestionsFunction tap1 = new TeacherAddQuestionsFunction();
		tap1.SaveNextQuestion(PaperID,QusetionID, Question,AnswerA,AnswerB,AnswerC,AnswerD,noQuestions,correctAnswer);
		
		
	}
	

		
		
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		Drawer.toBack();
		
		TeacherAddPaperFunction adpf = new TeacherAddPaperFunction(); //set questionid on label by adding one to paperid
		String questionID =  adpf.paperIDgenerate () +  String.valueOf(1);
		quesetionIDlabel.setText(questionID);
	}
}
