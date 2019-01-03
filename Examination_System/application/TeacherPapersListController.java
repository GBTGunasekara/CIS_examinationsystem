package application;

import java.net.URL;

import java.rmi.RemoteException;
import java.util.Arrays;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.mysql.jdbc.Connection;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.util.Callback;

import tables.PaperListTable;
import tables.ViewClassTable;


public class TeacherPapersListController implements Initializable{

	@FXML 
	private Button Closebttn2, Minimizebttn2, ViewQuestionsbtn;
	
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;

	@FXML 
	private TableView<TeacherPaperListTable> viewPaperListTbl;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> PaperIDcol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> PaperPasswordcol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> ClassIDcol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> NoQecol;
	//@FXML 
	//private TableColumn<TeacherPaperListTable, String> NoAnscol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> createdatecol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> releaseDateCol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> terminateDateCol;
	@FXML 
	private TableColumn<TeacherPaperListTable, String> stCountCol;
	
	
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
	private void MovetoViewQuestions(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherViewQuestionsGUI.fxml";
		fxmlLoader(link);
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		
		//Reference (You Tube video)
		// B2 Tech (2017).JavaFx Database Operations - Part 8 - Display the DB values in TableView. [Video] Available at: https://www.youtube.com/watch?v=L8iuBXl-F8U [Accessed Day : 17-12-2018].
		
		PaperIDcol.setCellValueFactory(cellData -> cellData.getValue().getPID());
		PaperPasswordcol.setCellValueFactory(cellData -> cellData.getValue().getPwd());
		ClassIDcol.setCellValueFactory(cellData -> cellData.getValue().getCID());
		NoQecol.setCellValueFactory(cellData -> cellData.getValue().getNoQ());
		//NoAnscol.setCellValueFactory(cellData -> cellData.getValue().getNoAns());
		createdatecol.setCellValueFactory(cellData -> cellData.getValue().getCtDate());
		releaseDateCol.setCellValueFactory(cellData -> cellData.getValue().getReDateTime());
		terminateDateCol.setCellValueFactory(cellData -> cellData.getValue().getTeDateTime());
		stCountCol.setCellValueFactory(cellData -> cellData.getValue().getstCount());
		
		
		try {
			String teacherID = "TID123";
			ObservableList<TeacherPaperListTable> plist = TeacherPapersListFunction.selcetPaperList1(teacherID);
			viewPaperListTbl.setItems(plist);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}