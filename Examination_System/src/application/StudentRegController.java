package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	
	//eaxmple
	@FXML
	private TextField Stid; 
	@FXML
	private TextField Stid2; 
	@FXML
	private Button Save; 
	
	@FXML
	private void showt(MouseEvent event)
	{
		String st = Stid.getText();
		Stid2.setText(st);	
	}
	
	//exover
	
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
        } 
        catch (IOException ex)
        {
            System.out.println(ex);
        }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//Image pp1 = new Image("/image/Michael-Stone-Death.jpg",false);
		//ppcirlce.setFill(new ImagePattern(pp1));
		
	}
	
}
