package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.mysql.jdbc.Connection;

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
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	private TableView<PaperListTable> viewPaperListTbl;
	@FXML 
	private TableColumn<PaperListTable, String> col_Pid;
	@FXML 
	private TableColumn<PaperListTable, String> col_Ppwd;
	@FXML 
	private TableColumn<PaperListTable, String> col_clID;
	@FXML 
	private TableColumn<PaperListTable, String> col_numQ;
	@FXML 
	private TableColumn<PaperListTable, String> col_numA;
	@FXML 
	private TableColumn<PaperListTable, String> col_rDate;
	@FXML 
	private TableColumn<PaperListTable, String> col_tDate;
	@FXML 
	private TableColumn<PaperListTable, String> col_numStd;
	
	
	ObservableList<PaperListTable> oblist = FXCollections.observableArrayList();
	
	ResultSet rs = null;
	
	public void tableload() {
		try {
		Connection con = DBconnection.Connect();
		
			rs= con.createStatement().executeQuery("Select paperID, pePassword, classID, numQuestion, numAnswers, releseDate,"
					+ " terminateDate, createDateTime from paper");
			
			while(rs.next()) {
				
				
				oblist.add(new PaperListTable(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	}

}
