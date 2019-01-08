package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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

public class TeacherViewQuestionsController implements Initializable {

	@FXML
	private Button Closebttn, Minimizebttn, ClassListbtn, viewbtn;
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
	private Label ansNolbl;
//	@FXML
//	private ComboBox<Character> AnsNoCombo;
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
	public void setTeacherViewPaperDetails (String paperid, String noQuestion) throws RemoteException, MalformedURLException, NotBoundException
	{
		//set created paper details on StudentAnswerPaperGUI 
		paperIDlabel.setText(paperid); 
		//classIDlabel.setText(classID);
	//	teacherIDlabel.setText(teacherID);
		
		//insert given question numbers to combo box according to previous entry
		int numbers_to_add_max = Integer.parseInt(noQuestion);
		for ( int i = 1; i <= numbers_to_add_max; i++)  
		{
			QuestionNoCombo.getItems().add(new Integer(i));
		}
		
		QuestionNoCombo.getSelectionModel().selectFirst(); // select 1st index of combo box
		
		String questionID =  paperid +  String.valueOf(1); //create questionID according to paperID
		quesetionIDlabel.setText(questionID); //set it on quesetionIDlabel label
		
	
		setQuestion_Answer(paperid,0,Integer.parseInt(noQuestion)); //call setQuestion_Answer method passed values to set first question of the paper when page load
	}
	

	@FXML
	private void NextQuestion(MouseEvent event) throws Exception
	{


			int QuestionNo;
			QuestionNo = Integer.parseInt(QuestionNoCombo.getSelectionModel().getSelectedItem().toString()); //get the current question number
			//String AnswerNo = AnsNoCombo.getSelectionModel().getSelectedItem().toString(); // get the answer of the current question
			
			ObservableList<Integer> totQuestionlist = QuestionNoCombo.getItems(); // get the combo box values into a observer list to get total questions 
			int totQuestion = totQuestionlist.size(); //get item count of combobox which equals total number questions in this paper
			
			if (QuestionNo == totQuestion)
			{
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/TeacherPapersListGUI.fxml"));
				Parent root = loader.load();
				stage.initStyle(StageStyle.UNDECORATED);
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
				
				Stage stage2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
				stage2.close();
				
			}
			else if (QuestionNo == totQuestion-1)
			{
				Nextbtn.setText("Cancel");
				setQuestion_Answer (paperIDlabel.getText(),QuestionNo,totQuestion);//call methods of next question sets
				QuestionNoCombo.getSelectionModel().select(QuestionNoCombo.getSelectionModel().getSelectedIndex()  +1);// increase the Question number by one
			}
			else
			{
				//saveAnswer(paperIDlabel.getText(),QuestionNo,AnswerNo,quesetionIDlabel.getText()); //save the answer
				setQuestion_Answer (paperIDlabel.getText(),QuestionNo,totQuestion);//call methods of next question sets
				QuestionNoCombo.getSelectionModel().select(QuestionNoCombo.getSelectionModel().getSelectedIndex()  +1);// increase the Question number by one
				//AnsNoCombo.getSelectionModel().select(null); //clear AnsNoCombo Combo Box value
			}
//		}
//		else
//		{
//			JOptionPane.showMessageDialog(null, "Selcet an Answer");
//		}
	}
	
	public void setQuestion_Answer (String paperID, int Qno, int numofQs) throws RemoteException, MalformedURLException, NotBoundException
	{
		TeacherViewQuestionsFunction tvqf = new TeacherViewQuestionsFunction();
		String [][] Questionlist = tvqf.loadQuestionsList(paperID, numofQs);
		
		String nextQuestion = Questionlist[Qno][1]; // get next question 
		String QuestionID = Questionlist[Qno][0]; //get the next questionID
		
		quesetionIDlabel.setText(QuestionID); // set question ID
		QuestionTextArea.setText(nextQuestion); //set question 
		
		String[][] AnswerList = tvqf.loadAnswerlist(paperID, Qno);
		
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
		
		String ansno =  tvqf.setAnswerNo(QuestionID);
		ansNolbl.setText(ansno);
	
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		Drawer.toBack();
	}


	
}
