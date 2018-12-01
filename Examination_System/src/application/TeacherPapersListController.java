package application;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class TeacherPapersListController implements Initializable{

	@FXML 
	private Button Closebttn2, Minimizebttn2, ViewQuestionsbtn;
	
	@FXML
	private JFXHamburger Hamburger;
	@FXML
	private JFXDrawer Drawer;
	@FXML
	private TableView  paperTable;
	@FXML
	private TableColumn<?, ?>  PaperIDcol;
	@FXML
	private TableColumn  PaperPasswordcol;
	@FXML
	private TableColumn  ClassIDcol;
	@FXML
	private TableColumn  NoQecol;
	@FXML
	private TableColumn  NoAnscol;
	@FXML
	private TableColumn  releasecol;
	@FXML
	private TableColumn  terminatecol;
	@FXML
	private TableColumn  noStudentscol;
	@FXML
	private TableColumn  createdatecol;
	
	
	
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

	
	public void loadPaperListTable () throws RemoteException
	{
		String teacherID = "sdsd";
		
		TeacherPapersListFunction tplf = new TeacherPapersListFunction();
		String[][] paperList = tplf.selcetPaperList(teacherID);
		int papercount = tplf.paperCount(teacherID);
		
		
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(paperList));
        //data.remove(0);//remove titles from data
        
       /* TableView<String[]> paperTable = new TableView<>();
       for (int i = 0; i < paperList[0].length; i++) {
            TableColumn tc = new TableColumn(paperList[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
           tc.setPrefWidth(90);
           paperTable.getColumns().add(tc);
        }*/
        
       /* PaperIDcol.setCellValueFactory(new PropertyValueFactory<>("clID"));
        PaperPasswordcol.setCellValueFactory(new PropertyValueFactory<>("clName"));
        ClassIDcol.setCellValueFactory(new PropertyValueFactory<>("grade"));
        NoQecol.setCellValueFactory(new PropertyValueFactory<>("subName"));
        NoAnscol.setCellValueFactory(new PropertyValueFactory<>("location"));
        createdatecol.setCellValueFactory(new PropertyValueFactory<>("noStudents"));*/
		
		
        paperTable.setItems(data);
        
        
		
		
		
		
		/*for (int i=0; i < papercount; i++)
		{
			for(int j=0; j < 6; j++)
			{
				paperTable.setRowFactory(paperList[i][j]);
			}
		}*/
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		
		/*try {
			loadPaperListTable();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
