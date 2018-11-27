package application;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
		for ( int i = 1; i <= numbers_to_add_max; i++)  //set question numbers according to previous entry
		{
			QuestionNoCombo.getItems().add(new Integer(i));
		}
		QuestionNoCombo.getSelectionModel().selectFirst(); // select 1st index of combo box
		
		String questionID =  paperid +  String.valueOf(1); //create questionID according to paperID
		quesetionIDlabel.setText(questionID);
	
		for ( int i = 65; i <= 68; i++) //set A-D in answer combo box
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
		
		String correctAnswer =null;
		int noQuestions = Integer.parseInt(QuestionNoCombo.getSelectionModel().getSelectedItem().toString());
		if(AnsNoCombo.getSelectionModel().getSelectedItem()!= null)
		{
			 correctAnswer = AnsNoCombo.getSelectionModel().getSelectedItem().toString();
			 try
			 {
				 TeacherAddQuestionsFunction tap1 = new TeacherAddQuestionsFunction();
				 tap1.SaveNextQuestion(PaperID,QusetionID, Question,AnswerA,AnswerB,AnswerC,AnswerD,noQuestions,correctAnswer);
			 }
			 catch (Exception e)
			 {
				 System.out.println("method not working");
			 }
			 //clear text area and ready the GUI for next question and answers
			 QuestionTextArea.setText(""); 
			 AnswerATextArea.setText("");
			 AnswerBTextArea.setText("");
			 AnswerCTextArea.setText("");
			 AnswerDTextArea.setText("");
			 
			 
			 QuestionNoCombo.getSelectionModel().select(QuestionNoCombo.getSelectionModel().getSelectedIndex()  +1); //set next question number on question combo box by adding 1 to selected index
			 AnsNoCombo.getSelectionModel().select(null); // set null on next question's answer at the beginning 
			 
			 //increment the questionID by 1
             int papernum_len = QusetionID.length();
             String letters_set = QusetionID.substring(0,2);
             String numbers_set = QusetionID.substring(2, papernum_len);
             int numbers_set_int = Integer.parseInt(numbers_set);
             numbers_set_int = numbers_set_int + 1;
             numbers_set = Integer.toString(numbers_set_int);
             String NewQusetionID = (letters_set + numbers_set); 
             quesetionIDlabel.setText(NewQusetionID);
             
             
             
             
		}	
		else 
		{
			JOptionPane.showMessageDialog(null, "Selcet Correct Answer");
	    	    
			
		}	
	}
	

		
		
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		Drawer.toBack();
		
		//TeacherAddPaperFunction adpf = new TeacherAddPaperFunction(); //set questionid on label by adding one to paperid
		
	}
}
