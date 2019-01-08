package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
	private void handleClose(MouseEvent event) //close button
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void handleMinimize(MouseEvent event) //minimize button
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	
	
	
	@FXML
	private void MovetoResultPaper(MouseEvent event) throws Exception //Answer paper button action -> move onto paper result GUI
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
		
		StudentResultPaperController stapf = loader.getController(); //create object of StudentResultPaperController class for move relevant data to it
		stapf.paperResults(teacherIDlabel.getText(), classIDlabel.getText(), paperIDlabel.getText(), totQuestion);
	}
	
	public void setStudentPaperDetails (String paperid, String noQuestion, String classID, String teacherID) throws RemoteException, MalformedURLException, NotBoundException
	{
		//set created paper details on StudentAnswerPaperGUI 
		paperIDlabel.setText(paperid); 
		classIDlabel.setText(classID);
		teacherIDlabel.setText(teacherID);
		
		//insert given question numbers to combo box according to previous entry
		int numbers_to_add_max = Integer.parseInt(noQuestion);
		for ( int i = 1; i <= numbers_to_add_max; i++)  
		{
			QuestionNoCombo.getItems().add(new Integer(i));
		}
		
		QuestionNoCombo.getSelectionModel().selectFirst(); // select 1st index of combo box
		
		String questionID =  paperid +  String.valueOf(1); //create questionID according to paperID
		quesetionIDlabel.setText(questionID); //set it on quesetionIDlabel label
		
		//set A-D in answer AnsNoCombo combo box
		for ( int i = 65; i <= 68; i++) 
		{
			AnsNoCombo.getItems().add((char)i);
		}
		
		setFirstQuestionAnswer(paperid); //call setFirstQuestionAnswer method for set first question of the paper when page load
	}
	
	public Object interfaceClass() throws MalformedURLException, RemoteException, NotBoundException
	{
	
	StudentAnswerPaperFunctionInterface  SearchPe = (StudentAnswerPaperFunctionInterface) 
			Naming.lookup("rmi://localhost:1099/StudentAnswerPaper");
	//StudentAnswerPaperFunction sapf = new StudentAnswerPaperFunction();
	return SearchPe;
	}
	
	//set the first question of the paper when paper loading
	public void setFirstQuestionAnswer (String paperID) throws RemoteException, MalformedURLException, NotBoundException
	{
		
		String fisrtQuestion = ((StudentAnswerPaperFunctionInterface) interfaceClass()).selectFirstQuestion(paperID); //called selectFirstQuestion() method and initialize its' return value to a String 
		
		QuestionTextArea.setText(fisrtQuestion); //set first question on QuestionTextArea text area
		
		String[] AnswerList = ((StudentAnswerPaperFunctionInterface) interfaceClass()).selectFirstAnswerset(paperID);

		 //AnswerList = sapf.selectFirstAnswerset(paperID);
		 
		 AnswerATextArea.setText(AnswerList[0]);
		 AnswerBTextArea.setText(AnswerList[1]);
		 AnswerCTextArea.setText(AnswerList[2]);
		 AnswerDTextArea.setText(AnswerList[3]);
		
	}
	
	public void setQuestion_Answer (String paperID, int Qno, int numofQs) throws RemoteException, MalformedURLException, NotBoundException
	{
		
		String [][] Questionlist = ((StudentAnswerPaperFunctionInterface) interfaceClass()).loadQuestionsList(paperID, numofQs);
		
		String nextQuestion = Questionlist[Qno][1]; // get next question 
		String QuestionID = Questionlist[Qno][0]; //get the next questionID
		
		quesetionIDlabel.setText(QuestionID); // set question ID
		QuestionTextArea.setText(nextQuestion); //set question 
		
		String[][] AnswerList = ((StudentAnswerPaperFunctionInterface) interfaceClass()).loadAnswerlist(paperID, Qno);
		
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
	
	}
	 

//	public void loadQuestion(ActionEvent event) throws RemoteException, MalformedURLException, NotBoundException, SQLException 
//	{
//		
//		
//		if (AnsNoCombo.getSelectionModel().getSelectedItem()!= null)
//		{
//			int QuestionNo = Integer.parseInt(QuestionNoCombo.getSelectionModel().getSelectedItem().toString());
//			//Integer QuestionNo =  QuestionNoCombo.getValue();
//			ObservableList<Integer> totQuestionlist = QuestionNoCombo.getItems(); // get the combo box values into a observer list to get total questions 
//			int totQuestion = totQuestionlist.size(); //get item count of combobox which equals total number questions in this paper
//			//setQuestion_Answer(paperIDlabel.getText(), QuestionNo , totQuestion);
//			
//			String [][] Questionlist = ((StudentAnswerPaperFunctionInterface) interfaceClass()).loadQuestionsList(paperIDlabel.getText(), totQuestion);
//			
//			String nextQuestion = Questionlist[QuestionNo-1][1]; // get next question 
//			String QuestionID = Questionlist[QuestionNo-1][0]; //get the current questionID
//			
//			quesetionIDlabel.setText(QuestionID); // set question ID
//			QuestionTextArea.setText(nextQuestion); //set question 
//			
//			String[][] AnswerList = ((StudentAnswerPaperFunctionInterface) interfaceClass()).loadAnswerlist(paperIDlabel.getText(), QuestionNo);
//			
//			//initializing answers
//			String AnswerA = AnswerList[0][1];
//			String AnswerB = AnswerList[1][1];
//			String AnswerC = AnswerList[2][1];
//			String AnswerD = AnswerList[3][1];
//			
//			//set answers to relevant text areas
//			AnswerATextArea.setText(AnswerA);
//			AnswerBTextArea.setText(AnswerB);
//			AnswerCTextArea.setText(AnswerC);
//			AnswerDTextArea.setText(AnswerD);
//		}
//		else
//		{
//			System.out.println("question 1 already printed");
//			
//		}
//	}
	
	public void saveAnswer (String paperID, int Qno, String answerNo, String questionID) throws RemoteException, MalformedURLException, NotBoundException
	{
		String StudentID = "STID012546" ;
		String[][] AnswerList = ((StudentAnswerPaperFunctionInterface) interfaceClass()).loadAnswerlist(paperID, Qno);
		//initializing answer id 
				String AnswerAID = AnswerList[0][0];
				String AnswerBID = AnswerList[1][0];
				String AnswerCID = AnswerList[2][0];
				String AnswerDID = AnswerList[3][0];
				

				 
				 
				 if (answerNo.equals("A"))
					 ((StudentAnswerPaperFunctionInterface) interfaceClass()).InsertStudentAnswer(StudentID, paperID, questionID, AnswerAID);
				 else if (answerNo.equals("B"))
					 ((StudentAnswerPaperFunctionInterface) interfaceClass()).InsertStudentAnswer(StudentID, paperID , questionID, AnswerBID);
				 else if (answerNo.equals("C"))
					 ((StudentAnswerPaperFunctionInterface) interfaceClass()).InsertStudentAnswer(StudentID, paperID, questionID, AnswerCID);
				 else if (answerNo.equals("D"))
					 ((StudentAnswerPaperFunctionInterface) interfaceClass()).InsertStudentAnswer(StudentID, paperID, questionID, AnswerDID);
	}
	
	@FXML
	private void NextQuestion(MouseEvent event) throws Exception
	{
		if (AnsNoCombo.getSelectionModel().getSelectedItem()!= null) //check answer combo is empty or not
		{

			int QuestionNo;
			QuestionNo = Integer.parseInt(QuestionNoCombo.getSelectionModel().getSelectedItem().toString()); //get the current question number
			String AnswerNo = AnsNoCombo.getSelectionModel().getSelectedItem().toString(); // get the answer of the current question
			
			ObservableList<Integer> totQuestionlist = QuestionNoCombo.getItems(); // get the combo box values into a observer list to get total questions 
			int totQuestion = totQuestionlist.size(); //get item count of combobox which equals total number questions in this paper
			
			if (QuestionNo == totQuestion)
			{
			
				JOptionPane.showMessageDialog(null, "You have answered to all questions");
			/*QuestionNo = 1;
			setQuestion_Answer (paperIDlabel.getText(),QuestionNo,totQuestion);
			QuestionNoCombo.getSelectionModel().select(0);// set combobox value to number one*/
			}
			else
			{
				saveAnswer(paperIDlabel.getText(),QuestionNo,AnswerNo,quesetionIDlabel.getText()); //save the answer
				setQuestion_Answer (paperIDlabel.getText(),QuestionNo,totQuestion);//call methods of next question sets
				QuestionNoCombo.getSelectionModel().select(QuestionNoCombo.getSelectionModel().getSelectedIndex()  +1);// increase the Question number by one
				AnsNoCombo.getSelectionModel().select(null); //clear AnsNoCombo Combo Box value
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selcet an Answer");
		}
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StudentDrawerController ad1 = new StudentDrawerController();
		ad1.StudentDrawer(Hamburger, Drawer);
		Drawer.toBack();
		
	
	
	
	}
}
