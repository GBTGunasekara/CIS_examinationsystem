package application;

import java.awt.HeadlessException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TeacherViewResultsController implements Initializable{

	@FXML
	private Button Closebttn, Minimizebttn;
	@FXML
	private Button Searchbtn;
	@FXML
	private Button printbtn;
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private TextField paperIDtxt;
	@FXML
	private TextField paperIDtxt2;
	@FXML
	private TextField teacherIDtxt;
	@FXML
	private TextField classIDtxt;
	@FXML
	private TextField noQusetiontxt;
	@FXML
	private TextField ReleaseDateTimetxt;
	@FXML
	private TextField TerminateDateTimetxt;
	@FXML 
	private TableView<TeacherViewResultsTable> viewPaperResultTbl;
	@FXML 
	private TableColumn<TeacherViewResultsTable, String> studentIDcol;
	@FXML 
	private TableColumn<TeacherViewResultsTable, String> studentNameCol;
	@FXML 
	private TableColumn<TeacherViewResultsTable, String> MarksCol;
	@FXML 
	private TableColumn<TeacherViewResultsTable, String> ansdatecol;
	
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
	private void showResultList(MouseEvent event) throws HeadlessException, RemoteException
	{
		
		String paperID = paperIDtxt.getText(); 
		
	/*	StudentSelectPaperInterface  SearchPe = (StudentSelectPaperInterface) 
				Naming.lookup("rmi://localhost:1099/StudentSelectPaper");*/
		
		TeacherViewResultsFunction tvrf = new TeacherViewResultsFunction();
		
		if  (paperID.equals(""))
		{
				JOptionPane.showMessageDialog(null, "Insert paperID ");
		}
		else
		{
		try {
			if (tvrf.paperIDcheck(paperID) == true)
			{
				String peDetArr[] = tvrf.paperDetails(paperID);
				
				paperIDtxt2.setText(peDetArr[0]);
				classIDtxt.setText(peDetArr[1]);
				teacherIDtxt.setText(peDetArr[2]);
				noQusetiontxt.setText(peDetArr[3]);
				ReleaseDateTimetxt.setText(peDetArr[4]);
				TerminateDateTimetxt.setText(peDetArr[5]);
				
				studentIDcol.setCellValueFactory(cellData -> cellData.getValue().getSID());
				studentNameCol.setCellValueFactory(cellData -> cellData.getValue().getstName());
				MarksCol.setCellValueFactory(cellData -> cellData.getValue().getMk());
				ansdatecol.setCellValueFactory(cellData -> cellData.getValue().getAnsTime());


				try {
					String paeprID = paperIDtxt.getText();
					ObservableList<TeacherViewResultsTable> tvrt = TeacherViewResultsFunction.selcetPaperResultsList(paeprID);
					viewPaperResultTbl.setItems(tvrt);
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid paperID");
			}
		} catch (SQLException e) 
		{
			System.out.println(e);
		}
		}
		
		
		//Reference (You Tube video)
		// B2 Tech (2017).JavaFx Database Operations - Part 8 - Display the DB values in TableView. [Video] Available at: https://www.youtube.com/watch?v=L8iuBXl-F8U [Accessed Day : 17-12-2018].
		
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		
		
			
	}
	
}
