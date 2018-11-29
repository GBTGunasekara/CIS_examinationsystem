package application;

import java.net.URL;
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
	private void MovetoResultPaper(MouseEvent event) throws Exception
	{
		String link = "/application/StudentResultPaperGUI.fxml";
		fxmlLoader(link);
		
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
		
		
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		StudentDrawerController ad1 = new StudentDrawerController();
		ad1.StudentDrawer(Hamburger, Drawer);
		Drawer.toBack();
	}
}
