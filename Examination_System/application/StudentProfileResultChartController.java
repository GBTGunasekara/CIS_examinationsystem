package application;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentProfileResultChartController {
	@FXML
	private Button Closebttn3;
	@FXML
	private Button Minimizebttn3;
	@FXML
	private LineChart<String,Integer> resultschart;
	@FXML
	private CategoryAxis Xaxis;
	@FXML
	private NumberAxis Yaxis;
	@FXML
	private TextField ClassIdbox;
	@FXML
	private TextField stCounttxt;
	@FXML
	private TextField stIDBox;

	
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
	public void setLineChart (MouseEvent event) throws SQLException
	{
		
			String ClID = ClassIdbox.getText();
			//String stID = stIDBox.getText();
			String stID = "SID1";
			
			
			StudentProfileResultChartFunction sprcf = new StudentProfileResultChartFunction();
		
			
			String stMarks[][] =  sprcf.ViewMarks(stID,ClID);
			
			XYChart.Series<String, Integer> set = new XYChart.Series<>();
			
			int arRowcount = sprcf.getRowCount(stID, ClID);
			for(int i =0; i<1; i++) {
				int numQ = 0;											//calculate Percentage
				int percen = 0;
				numQ = sprcf.getNumQuestions(stMarks[i][0]);
				percen = (Integer.parseInt(stMarks[i][1])*100)/numQ;
				
				
				set.getData().add(new XYChart.Data<String, Integer>(stMarks[i][0], percen));
			}
			
			
			resultschart.getData().addAll(set);
			//resultschart.setAnimated(false); // prevent the x axis label overlay by disable the animation. 
	
	}
	
}
