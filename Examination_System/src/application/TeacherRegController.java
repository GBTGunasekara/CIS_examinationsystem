package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;

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
	
	
	@FXML
	private void SaveTeacherReg(MouseEvent event) throws FileNotFoundException, ParseException 
	{
		
		String teID = teIDtxt.getText();
		String teName = teNametxt.getText();
		String teEmail = teEmailtxt.getText();
		String tePassword = tePasswordtxt.getText();
		String teRePassword = teRePasswordtxt.getText();
		
		//SimpleDateFormat dateformat =new SimpleDateFormat("dd/MM/YYYY ");
		//String DOB = dateformat.format(teDOB.getValue());
		
		//String DOB = teDOB.getEditor().getText();
		String DOB = teDOB.getPromptText();
		
		//DateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd");
		//Date dob1 = dateformat.format(DOB);
		Date date1=(Date) new SimpleDateFormat("dd/MM/yyyy").parse(DOB);
		
		InputStream imagePath = new FileInputStream(new File(path));
		
		String teGender = null ;
		
		if (teMale.isSelected())
			teGender = teMale.getText();
		else
			teGender = teFemale.getText();
		
		TeacherRegFunction tr1 = new TeacherRegFunction();
		 tr1.createTeacherAccount(teID,teName,teEmail,tePassword,date1,teGender,imagePath);
		
		/*String teID = teIDtxt.getText();
		TeacherRegFunction tr1 = new TeacherRegFunction();
		 tr1.testingdb(teID);*/
		
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
        	
            if(file != null){
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
    		ppcirlce2.setFill(new ImagePattern(image));
    		
    		path = file.getAbsolutePath();
    		
            }
            else{
                System.out.println("No Data");
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
