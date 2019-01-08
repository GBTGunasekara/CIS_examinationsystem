package application;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
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
	private BarChart<String,Integer> resultschart;
	@FXML
	private CategoryAxis Xaxis;
	@FXML
	private NumberAxis Yaxis;
	@FXML
	private TextField ClassIdbox;
	@FXML
	private TextField stCounttxt;

	
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
	
	public void setLineChart (String paperID) throws SQLException
	{
		
			ClassIdbox.setText(paperID);
			
			TeacherViewResultChartFunction tvrcf = new TeacherViewResultChartFunction();
			
			int stcount = tvrcf.getStudentCount(paperID);
			stCounttxt.setText(String.valueOf(stcount));
			
			int groupStudents[] = tvrcf.analyzeMarks(paperID);
			
			XYChart.Series<String, Integer> set = new XYChart.Series<>();
			
			set.getData().add(new XYChart.Data<String, Integer>("<35", groupStudents[0]));
			set.getData().add(new XYChart.Data<String, Integer>("45-55", groupStudents[1]));
			set.getData().add(new XYChart.Data<String, Integer>("55-65", groupStudents[2]));
			set.getData().add(new XYChart.Data<String, Integer>("65-75", groupStudents[3]));
			set.getData().add(new XYChart.Data<String, Integer>(">75", groupStudents[4]));
			
			resultschart.getData().addAll(set);
			resultschart.setAnimated(false); // prevent the x axis label overlay by disable the animation. 
	
	}
	
}
