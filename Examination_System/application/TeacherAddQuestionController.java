package application;


//import java.awt.event.ActionEvent;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.event.ActionEvent;
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
	private Label classIDlabel;
	@FXML
	private Label teacherIDlabel;
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
	private Button Clearbtn;
	@FXML
	private Label systemTimelbl;
	
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
		
		
		
		ObservableList<Integer> totQuestionlist = QuestionNoCombo.getItems();//insert combo box items to observable list for get total number of questions
		String totQuestion = String.valueOf(totQuestionlist.size()); //get item count of observable list and initialize it to totQuestion
		
		ObservableList<Character> totAnslist = AnsNoCombo.getItems();//insert combo box items to observable list for get total number of answers per question		
		String totAns = String.valueOf(totAnslist.size()); //get item count of observable list and initialize it to totAns
		
		TeacherAddQuestionsFunction taqf = new TeacherAddQuestionsFunction();
		int savedQuestionsCount = taqf.checkQuestionsSaved(paperIDlabel.getText());
		
		if (Integer.parseInt(totQuestion)== savedQuestionsCount)
		{
			TeacherSubmitPaperController tspc = loader.getController();
			tspc.paperDetails( teacherIDlabel.getText(),  classIDlabel.getText(),  paperIDlabel.getText(),  totQuestion,  totAns ); //pass need values to next panel
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Save all questions before save the paper");
		}
	}
	
	@FXML
	private void MovetoSubmitPaper(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherSubmitPaperGUI.fxml";
		fxmlLoader(link);
			
	}

	public void setTeacherPaperDetails (String paperid, int noQuestion, String classID, String teacherID)
	{
		paperIDlabel.setText(paperid); //set created paperID on teacherAddQuestionGUI 
		classIDlabel.setText(classID);
		teacherIDlabel.setText(teacherID);
		
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
	private void SaveEnterNextQuestion(MouseEvent event) throws RemoteException, SQLException 
	{
		//get required values from window
		String PaperID = paperIDlabel.getText();
		String QusetionID= quesetionIDlabel.getText();
		String Question = QuestionTextArea.getText();
		String AnswerA = AnswerATextArea.getText();
		String AnswerB = AnswerBTextArea.getText();
		String AnswerC= AnswerCTextArea.getText();
		String AnswerD = AnswerDTextArea.getText();
		
		String correctAnswer =null;
		int noQuestions = Integer.parseInt(QuestionNoCombo.getSelectionModel().getSelectedItem().toString()); //initialize currently selected question number 
		
		
		//TeacherAddPaperController tapc = new TeacherAddPaperController();
		//int totQuestion =  tapc.passQuestionCount(); // get totQuestion form TeacherAddPaperController class
				
		ObservableList<Integer> totQuestionlist = QuestionNoCombo.getItems();		
		
		int totQuestion = totQuestionlist.size();
		
		
		if(AnsNoCombo.getSelectionModel().getSelectedItem()!= null)
		{

			 correctAnswer = AnsNoCombo.getSelectionModel().getSelectedItem().toString();
			 try
			 {
				 /*TeacherAddQuestionsFunctionInterface  nextQe = (TeacherAddQuestionsFunctionInterface) 
							Naming.lookup("rmi://localhost:1099/TeacherAddQuestions");*/
				 TeacherAddQuestionsFunction tap1 = new TeacherAddQuestionsFunction();
				 tap1.SaveNextQuestion(PaperID,QusetionID, Question,AnswerA,AnswerB,AnswerC,AnswerD,noQuestions,correctAnswer);
			 }
			 catch (Exception e)
			 {
				 System.out.println("method not working");
			 }
			 
			 if (noQuestions == totQuestion) //preventing move on to next question which out of the limit of totQuestion 
				{
				 TeacherAddQuestionsFunction taqf = new TeacherAddQuestionsFunction();
					int savedQuestionsCount = taqf.checkQuestionsSaved(paperIDlabel.getText());
					
					if (totQuestion == savedQuestionsCount) //check all the questions save
					{
						JOptionPane.showMessageDialog(null, "You have entered all questions");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You have to enter some questions. Use Combo Box to move onto questions");
					}
				 
				 
					
				}
			 else
			 {
			 //clear text area and ready the GUI for next question and answers
			 QuestionTextArea.setText(""); 
			 AnswerATextArea.setText("");
			 AnswerBTextArea.setText("");
			 AnswerCTextArea.setText("");
			 AnswerDTextArea.setText("");
			 
			 
			 QuestionNoCombo.getSelectionModel().select(QuestionNoCombo.getSelectionModel().getSelectedIndex()  +1); //set next question number on question combo box by adding 1 to selected index
			 AnsNoCombo.getSelectionModel().select(null); // set null on next question's answer at the beginning 
			 
			 //increment the questionID by 1
             int questionNumLen = QusetionID.length();
             String lettersset = QusetionID.substring(0,2);
             String numbers_set = QusetionID.substring(2, questionNumLen);
             int numbers_set_int = Integer.parseInt(numbers_set);
             numbers_set_int = numbers_set_int + 1;
             numbers_set = Integer.toString(numbers_set_int);
             String NewQusetionID = (lettersset + numbers_set); 
             quesetionIDlabel.setText(NewQusetionID);
			 }
			
             
             
		}	
		else 
		{
			JOptionPane.showMessageDialog(null, "Selcet Correct Answer");
	    	    	
		}		
	}
	



	public void loadQuestion(ActionEvent event) 
	{
		//QuestionNoCombo.setOnAction((event) -> {
			int QuestionNo = Integer.parseInt(QuestionNoCombo.getSelectionModel().getSelectedItem().toString());
			String PaperID = paperIDlabel.getText();
			
			
			try {
			TeacherAddQuestionsFunction  taqf = new TeacherAddQuestionsFunction();
			
			if (taqf.checkComboQuestion(QuestionNo, PaperID) == true) //check is question already entered
			{

			//clear text area and ready the GUI for next question and answers
			 QuestionTextArea.setText(""); 
			 AnswerATextArea.setText("");
			 AnswerBTextArea.setText("");
			 AnswerCTextArea.setText("");
			 AnswerDTextArea.setText("");
			
				String loadquestion[] = taqf.selectComboQuestion (String.valueOf(QuestionNo),PaperID);
				quesetionIDlabel.setText(loadquestion[0]);
				QuestionTextArea.setText(loadquestion[1]);
				
				String loadanswers[][] = taqf.comboAnswerSet (loadquestion[0]);
				AnswerATextArea.setText(loadanswers[0][0]);
				AnswerBTextArea.setText(loadanswers[1][0]);
				AnswerCTextArea.setText(loadanswers[2][0]);
				AnswerDTextArea.setText(loadanswers[3][0]);
				
				for (int i=0; i<4; i++)
				{
					if(loadanswers[i][1].equals("Correct"))
					{
						AnsNoCombo.getSelectionModel().select(i);
					}
					else
					{
						System.out.println("it is not the correct answer");
					}
				}
				
			}
			else //if question is not entered previously 
			{
				QuestionTextArea.setText(""); 
				 AnswerATextArea.setText("");
				 AnswerBTextArea.setText("");
				 AnswerCTextArea.setText("");
				 AnswerDTextArea.setText("");
				 
				 String paperID= paperIDlabel.getText();
				 String newQuestionID = paperID + QuestionNoCombo.getSelectionModel().getSelectedItem().toString() ;
				 quesetionIDlabel.setText(newQuestionID);
				 AnsNoCombo.getSelectionModel().select(null);
			}
			}
			 
			catch (RemoteException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		//});
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
	
	//clear button
	@FXML
	public void clearArea (MouseEvent event)
	{
		 QuestionTextArea.setText(""); 
		 AnswerATextArea.setText("");
		 AnswerBTextArea.setText("");
		 AnswerCTextArea.setText("");
		 AnswerDTextArea.setText("");
		 AnsNoCombo.getSelectionModel().select(null);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		Drawer.toBack();
		liveDateTime();
	}
}
