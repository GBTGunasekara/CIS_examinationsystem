package application;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TeacherAddClassController implements Initializable{
	
	@FXML
	private Button Closebttn3, Minimizebttn3, savebtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	public JFXTextField teacherIDbox;
	@FXML
	public JFXTextField classIDbox;
	@FXML
	public JFXTextField subNamebox;
	@FXML
	public JFXTextField gradebox;
	@FXML
	public JFXTextField classNamebox;
	@FXML
	public JFXTextField locNamebox;
	@FXML
	public Button clearbtn;
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
	}
	
	@FXML
	private void CreateClass() throws RemoteException, MalformedURLException, NotBoundException 
	{
		
		int teID = Integer.parseInt(teacherIDbox.getText());
		int classID = Integer.parseInt(classIDbox.getText());
		int grade = Integer.parseInt(gradebox.getText());
		String subName = subNamebox.getText();
		String className = classNamebox.getText();
		String location = locNamebox.getText();

		TeacherAddClassFunctionInterface  tacf = (TeacherAddClassFunctionInterface) 
				Naming.lookup("rmi://localhost:1099/TeacherAddClass");
	    	    
		//TeacherAddClassFunction tac1 = new TeacherAddClassFunction();
		tacf.createClass(teID,classID,subName, grade,className,location);
		
	}
	
	@FXML
	private void clear(MouseEvent event) {
		
		teacherIDbox.setText("");
		classIDbox.setText("");
		gradebox.setText("");
		subNamebox.setText("");
		classNamebox.setText("");
		locNamebox.setText("");
		
	}
}
