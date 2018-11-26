package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StudentRegController implements Initializable{
	@FXML
	private Button Closebttn; 
	@FXML
	private Button Minimizebttn; 
	@FXML
	private Circle ppcirlce;
	@FXML
	private Button Stppbtn;
	
	@FXML
	private Button Save; 
	@FXML
	private JFXTextField stIDtxt;
	@FXML
	private JFXTextField stNametxt;
	@FXML
	private JFXTextField stEmailtxt;
	@FXML
	private JFXPasswordField stPasswordtxt;
	@FXML
	private JFXPasswordField stRePasswordtxt;
	@FXML
	private JFXDatePicker stDOB;
	@FXML
	private JFXRadioButton stMale;
	@FXML
	private JFXRadioButton stFemale;
	@FXML
	private ToggleGroup stGender;
	
	String path = null;
	String stGenderstr = null ;
	
	@FXML
	private void SaveStudentReg(MouseEvent event) throws FileNotFoundException, RemoteException 
	{
		
		String stID = stIDtxt.getText();
		String stName = stNametxt.getText();
		String stEmail = stEmailtxt.getText();
		String stPassword = stPasswordtxt.getText();
		String stRePassword = stRePasswordtxt.getText();
		
		
		
		
			if (stMale.isSelected())
				stGenderstr = stMale.getText();
			else if (stFemale.isSelected())
				stGenderstr = stFemale.getText();
			else
				JOptionPane.showMessageDialog(null, "Select Gender");
			
			LocalDate DOB = stDOB.getValue();
			
		
			//TeacherRegFunction tr1 = new TeacherRegFunction();
			//tr1.createTeacherAccount(teID,teName,teEmail,tePassword,DOB,teGender,path,teRePassword);
		
			try {
				
				StudentRegFunctionInterface  StudentReg = (StudentRegFunctionInterface) 
				Naming.lookup("rmi://localhost:1099/Student");
				
				//create thread
				Service<Void>src=new Service<Void>() {
					protected Task<Void>createTask(){
						return new Task<Void>() {
							protected Void call()throws Exception{
								StudentReg.createStudentAccount(stID,stName,stEmail,stPassword,DOB,stGenderstr,path,stRePassword);
								return null;
							}
						};
					}
				};
			    src.start();
				
			} catch (Exception e){
				System.out.println(" Failed to connect to Hello Server ");
			}
			
	}
	
	@FXML
	private void handleClose(MouseEvent event)
	{
		System.exit(0);
	}
	
	@FXML
	private void handleMinimize(MouseEvent event)
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	
	//Refernece - http://java-buddy.blogspot.com/2015/12/javafx-filechooser-to-open-image-file.html
	@FXML
	private void stPicChooser(MouseEvent event)
	{
		FileChooser fchooser = new FileChooser();
		
		//extention filter
		FileChooser.ExtensionFilter JPGFilter = new FileChooser.ExtensionFilter("JPG files (*.JPG)","*.JPG");
		FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("jpg files (*.jpg)","*.jpg");
		FileChooser.ExtensionFilter PNGFilter = new FileChooser.ExtensionFilter("PNG files (*.PNG)","*.PNG");
		FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("png files (*.png)","*.png");
		
		fchooser.getExtensionFilters().addAll(JPGFilter,jpgFilter,PNGFilter,pngFilter);
		
		//Show open file dialog
        File file = fchooser.showOpenDialog(null);
         
        try 
        {	
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
    		ppcirlce.setFill(new ImagePattern(image));
    		
    		path = file.getAbsolutePath();
    		
        } 
        catch (IOException ex)
        {
            System.out.println(ex);
        }
	}

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
	}
	
}
