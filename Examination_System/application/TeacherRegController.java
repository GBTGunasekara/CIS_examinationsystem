package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TeacherRegController implements Initializable {
	
	@FXML
	private Button Closebttn; 
	@FXML
	private Button Minimizebttn; 
	@FXML
	private Circle ppcirlce2;
	@FXML
	private Button Tppbtn;
	@FXML
	private Button Savebtn;
	@FXML
	private JFXTextField teIDtxt;
	@FXML
	private JFXTextField teNametxt;
	@FXML
	private JFXTextField teEmailtxt;
	@FXML
	private JFXPasswordField tePasswordtxt;
	@FXML
	private JFXPasswordField teRePasswordtxt;
	@FXML
	private JFXDatePicker teDOB;
	@FXML
	private JFXRadioButton teMale;
	@FXML
	private JFXRadioButton teFemale;
	@FXML
	private ToggleGroup teGender;
	
	String path = null;
	String teGenderstr;
	
	@FXML
	private void SaveTeacherReg(MouseEvent event) throws FileNotFoundException, RemoteException 
	{
		
		String teID = teIDtxt.getText();
		String teName = teNametxt.getText();
		String teEmail = teEmailtxt.getText();
		String tePassword = tePasswordtxt.getText();
		String teRePassword = teRePasswordtxt.getText();
		
		
		
	
			if (teMale.isSelected()) 
				teGenderstr = teMale.getText();
			else if (teFemale.isSelected())
				teGenderstr = teFemale.getText();
			else
				JOptionPane.showMessageDialog(null, "Select Gender");
			
			LocalDate DOB = teDOB.getValue();
			
		
			try {
				
				TeacherRegFunctionInterface  TeacherReg = (TeacherRegFunctionInterface) 
				Naming.lookup("rmi://localhost:1099/TeacherReg");
				
				//create thread
				Service<Void>src=new Service<Void>() {
					protected Task<Void>createTask(){
						return new Task<Void>() {
							protected Void call()throws Exception{
								TeacherReg.createTeacherAccount(teID,teName,teEmail,tePassword,DOB,teGenderstr,path,teRePassword);
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
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
		
	}
	
	@FXML
	private void handleMinimize(MouseEvent event)
	{
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	
	//Refernece - http://java-buddy.blogspot.com/2015/12/javafx-filechooser-to-open-image-file.html
	@FXML
	private void tPicChooser(MouseEvent event)
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
        	
            if(file != null)
            {
            	BufferedImage bufferedImage = ImageIO.read(file);
            	Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            	ppcirlce2.setFill(new ImagePattern(image));
    		
            	path = file.getAbsolutePath();
    		
            }
            
        } 
        catch (IOException ex)
        {
            System.out.println(ex);
        }
	}
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//Image pp2 = new Image("/image/Best-ID-Passport-Photo-and-Corporate-Portraits-Team-ToBes-Photo_7.jpg",false);
		//ppcirlce2.setFill(new ImagePattern(pp2));
		
	}
}
