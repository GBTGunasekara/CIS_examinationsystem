package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TeacherViewResultChartController implements Initializable{

	@FXML
	private Button Closebttn3;
	@FXML
	private Button Minimizebttn3;
	@FXML
	private BarChart<?,?> resultschart;
	@FXML
	private CategoryAxis Xaxis;
	@FXML
	private NumberAxis Yaxis;

	
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
		XYChart.Series set = new XYChart.Series<>();
		
		set.getData().add(new XYChart.Data<>("<50", 5));
		set.getData().add(new XYChart.Data<>("50-60", 15));
		set.getData().add(new XYChart.Data<>("60-70", 25));
		set.getData().add(new XYChart.Data<>("70-80", 10));
		set.getData().add(new XYChart.Data<>(">80", 5));
		
		resultschart.getData().addAll(set);
	}
}
