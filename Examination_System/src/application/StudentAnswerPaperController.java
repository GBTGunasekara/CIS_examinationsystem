package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.collections.ObservableList;
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

public class StudentAnswerPaperController implements Initializable{
	@FXML
	private Button Closebttn3, Minimizebttn3;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private Label paperIDlabel;
	@FXML
	private Label quesetionIDlabel;
	@FXML
	private Label classIDlabel;
	@FXML
	private Label teacherIDlabel;
	/*@FXML
	private Label completeAnslabel;
	@FXML
	private Label QuestionNoLabel;
	@FXML
	private Label AnswerNoLabel;*/
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
	private Button Backbtn;
	
	
	
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
	
	
	
	@FXML
	private void MovetoResultPaper(MouseEvent event) throws Exception
	{
		
		
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StudentResultPaperGUI.fxml"));
	
		Parent root = loader.load();
	
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	
		ObservableList<Integer> totQuestionlist = QuestionNoCombo.getItems(); // get the combo box values into a observer list to get total questions 
		int totQuestion = totQuestionlist.size(); //get item count of combobox which equals total number questions in this paper
		
		StudentResultPaperController stapf = loader.getController();
		stapf.paperResults(teacherIDlabel.getText(), classIDlabel.getText(), paperIDlabel.getText(), totQuestion);
	}
	
	public void setStudentPaperDetails (String paperid, String noQuestion, String classID, String teacherID)
	{
		paperIDlabel.setText(paperid); //set created paperid on teacherAddQuestionGUI 
		classIDlabel.setText(classID);
		teacherIDlabel.setText(teacherID);
		
		//insert given question numbers to combo box
		int numbers_to_add_max = Integer.parseInt(noQuestion);
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
		
		
		setFirstQuestionAnswer(paperid); //set first question of the paper when page load

	}
	
	StudentAnswerPaperFunction sapf = new StudentAnswerPaperFunction();
	
	public void setFirstQuestionAnswer (String paperID)
	{
		
		String fisrtQuestion = sapf.selectFirstQuestion(paperID);
		
		QuestionTextArea.setText(fisrtQuestion);
		
		String[] AnswerList = sapf.selectFirstAnswerset(paperID);

		 //AnswerList = sapf.selectFirstAnswerset(paperID);
		 
		 AnswerATextArea.setText(AnswerList[0]);
		 AnswerBTextArea.setText(AnswerList[1]);
		 AnswerCTextArea.setText(AnswerList[2]);
		 AnswerDTextArea.setText(AnswerList[3]);
		
	}
	
	public void setQuestion_Answer (String paperID, int Qno, int numofQs, String answerNo)
	{
		
		String [][] Questionlist = sapf.loadQuestionsList(paperID, numofQs);
		
		String nextQuestion = Questionlist[Qno][1]; // set next question 
		String QuestionID = Questionlist[0][Qno-1]; //set the current questionID
		
		QuestionTextArea.setText(nextQuestion); //set question 
		
		String[][] AnswerList = sapf.loadAnswerlist(paperID, Qno);
		
		//initializing answers
		String AnswerA = AnswerList[0][1];
		String AnswerB = AnswerList[1][1];
		String AnswerC = AnswerList[2][1];
		String AnswerD = AnswerList[3][1];
		
		//set answers to relevant text areas
		AnswerATextArea.setText(AnswerA);
		AnswerBTextArea.setText(AnswerB);
		AnswerCTextArea.setText(AnswerC);
		AnswerDTextArea.setText(AnswerD);
		
		//initializing answer id 
		String AnswerAID = AnswerList[0][0];
		String AnswerBID = AnswerList[1][0];
		String AnswerCID = AnswerList[2][0];
		String AnswerDID = AnswerList[3][0];
		
		
		 
		 String StudentID = "STID012546" ; 
		 
		 if (answerNo.equals("A"))
			 sapf.InsertStudentAnswer(StudentID, paperID,AnswerAID);
		 else if (answerNo.equals("B"))
			 sapf.InsertStudentAnswer(StudentID, paperID ,AnswerBID);
		 else if (answerNo.equals("C"))
			 sapf.InsertStudentAnswer(StudentID, paperID,AnswerCID);
		 else if (answerNo.equals("D"))
			 sapf.InsertStudentAnswer(StudentID, paperID,AnswerDID);
		
	}
	
	@FXML
	private void NextQuestion(MouseEvent event) throws Exception
	{
		int QuestionNo;
		QuestionNo = Integer.parseInt(QuestionNoCombo.getSelectionModel().getSelectedItem().toString());
		String AnswerNo = AnsNoCombo.getSelectionModel().getSelectedItem().toString();
		
		ObservableList<Integer> totQuestionlist = QuestionNoCombo.getItems(); // get the combo box values into a observer list to get total questions 
		int totQuestion = totQuestionlist.size(); //get item count of combobox which equals total number questions in this paper
		
		if (QuestionNo == totQuestion)
		{
			QuestionNo = 1;
			setQuestion_Answer (paperIDlabel.getText(),QuestionNo,totQuestion,AnswerNo);
			QuestionNoCombo.getSelectionModel().select(0);// set combobox value to number one
		}
		else
		{
			setQuestion_Answer (paperIDlabel.getText(),QuestionNo,totQuestion,AnswerNo);
			QuestionNoCombo.getSelectionModel().select(QuestionNoCombo.getSelectionModel().getSelectedIndex()  +1);// increase the Question number by one
		}
	}
	
	/*@FXML
	private void BackQuestion(MouseEvent event) throws Exception
	{
		int QuestionNo = 0 ;
		QuestionNo = Integer.parseInt(QuestionNoCombo.getSelectionModel().getSelectedItem().toString());
		//String AnswerNo = AnsNoCombo.getSelectionModel().getSelectedItem().toString();
		
		ObservableList<Integer> totQuestionlist = QuestionNoCombo.getItems(); // get the combo box values into a observer list to get total questions 
		int totQuestion = totQuestionlist.size(); //get item count of combobox which equals total number questions in this paper
		
		if (QuestionNo == 1)
		{
			QuestionNo = totQuestion;
			setQuestion_Answer (paperIDlabel.getText(),QuestionNo,totQuestion);
			QuestionNoCombo.getSelectionModel().select(totQuestion - 1 );// set combobox value to maximum question number
		}
		else
		{
			setQuestion_Answer (paperIDlabel.getText(),QuestionNo,totQuestion);
			QuestionNoCombo.getSelectionModel().select(QuestionNoCombo.getSelectionModel().getSelectedIndex()- 1);// decrease the Question number by one
		}
	}*/
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StudentDrawerController ad1 = new StudentDrawerController();
		ad1.StudentDrawer(Hamburger, Drawer);
		Drawer.toBack();
		
	
	
	}
}
