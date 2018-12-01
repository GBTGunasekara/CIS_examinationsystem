package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableFocusModel;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tables.ViewClassTable;

public class TeacherViewClassController implements Initializable {
	@FXML
	private Button Closebttn, Minimizebttn, ClassListbtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML 
	private TableView<ViewClassTable> viewClassTbl;
	@FXML 
	private TableColumn<ViewClassTable, String> col_sID;
	@FXML 
	private TableColumn<ViewClassTable, String> col_sName;
	@FXML 
	private TableColumn<ViewClassTable, String> col_email;
	@FXML 
	private TableColumn<ViewClassTable, String> col_status;
	@FXML 
	private TableColumn<ViewClassTable, String> col_nPapers;
	
	
	
	ObservableList<ViewClassTable> oblist = FXCollections.observableArrayList(); 
	
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
	
	ResultSet rs = null;
	
	public void tableload() {
		//fill table
		try {
			Connection con = DBconnection.Connect();
			rs = con.createStatement().executeQuery("select student.studentID, student.stName, student.stEmail, student.stStatus, studentclass.noPapers from student "
					+ "Inner Join studentclass on student.studentID=studentclass.studentID");
			//PreparedStatement ps;
			//String q = "select student.studentID, student.stName, student.stEmail, student.stStatus, studentclass.noPapers from student "
					//+ "Inner Join studentclass on student.studentID=studentclass.studentID";
			//ps = (PreparedStatement) DBconnection.Connect().prepareStatement(q);
			//rs = ps.executeQuery();
			
			while (rs.next()) {
				oblist.add(new ViewClassTable(rs.getString("studentID"), rs.getString("stName"), rs.getString("stEmail"),
						rs.getString("stStatus"), rs.getString("noPapers")));
			}
			
			
		
		}catch(SQLException ex) {
			System.out.println(ex);
	    	JOptionPane.showMessageDialog(null, "error\n" + ex);
		}
		
		//table columns
		col_sID.setCellValueFactory(new PropertyValueFactory<>("sID"));
		col_sName.setCellValueFactory(new PropertyValueFactory<>("sName"));
		col_email.setCellValueFactory(new PropertyValueFactory<>("sEmail"));
		col_status.setCellValueFactory(new PropertyValueFactory<>("sStatus"));
		col_nPapers.setCellValueFactory(new PropertyValueFactory<>("nPaper"));
		
		/*try {
			fetch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	//@FXML
	/*private void fetch() throws Exception {
		
		TeacherViewClassFunction tap1 = new TeacherViewClassFunction();
		while(tap1.fillViewClass().next()) {
			oblist.add(new viewClassTable (tap1.fillViewClass().getString("studentID"), tap1.fillViewClass().getString("stName"),
			tap1.fillViewClass().getString("stEmail"), tap1.fillViewClass().getString("stStatus"), tap1.fillViewClass().getString("noPapers")));
		}
		//viewClassTbl.setModel(DbUtils.resultSetToTableModel(tap1.fillViewClass()));
		
	}*/
	
	@FXML
	private void MovetoClassList(MouseEvent event) throws Exception
	{
		String link = "/application/TeacherViewClassListGUI.fxml";
		fxmlLoader(link);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		
		/*TeacherViewClassController t1 = new TeacherViewClassController();
		t1.tableload();*/
		
	}
}
