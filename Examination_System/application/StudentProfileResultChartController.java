package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
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
	private TextField paperIDtxt;
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
}
