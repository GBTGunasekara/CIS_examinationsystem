package application;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TeacherViewResultsController implements Initializable{

	@FXML
	private Button Closebttn, Minimizebttn;
	@FXML
	private Button Searchbtn;
	@FXML
	private Button printbtn;
	@FXML
	private Button chartbtn;
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
	
	@FXML
	private void printResults(MouseEvent event) throws FileNotFoundException, DocumentException, RemoteException, SQLException
	{
		String savePath = null; 
		FileChooser fchooser = new FileChooser();

		fchooser.setInitialFileName("Paper ID " +paperIDtxt2.getText()+ " results list"); //set initial file name at filechooser
		
		//extention filter
		FileChooser.ExtensionFilter PDFilter = new FileChooser.ExtensionFilter("PDF file (*.PDF)","*.PDF");
		FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("pdf file (*.pdf)","*.pdf");
		fchooser.getExtensionFilters().addAll(PDFilter,pdfFilter);

        File file = fchooser.showSaveDialog(null); //Show open file dialog
         
        if (file != null) //make sure file is selected
		{       		
			savePath = file.getAbsolutePath();
		
		
		
		String fileName = savePath;
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream(fileName));
		doc.open();
		doc.add(new Paragraph("Result List",FontFactory.getFont(FontFactory.COURIER_BOLD,20,Font.UNDERLINE,BaseColor.BLACK)));
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph(" "));
		
		PdfPTable resultInfo = new PdfPTable(2);
		resultInfo.getDefaultCell().setBorderWidth(0f);
		resultInfo.setTotalWidth(new float[]{ 250, 250 });
		resultInfo.setLockedWidth(true);
		PdfPCell ri1 = new PdfPCell(new Paragraph("Paper ID - " + paperIDtxt2.getText()));
		PdfPCell ri2 = new PdfPCell(new Paragraph("Teacher ID - "+ teacherIDtxt.getText()));
		PdfPCell ri3 = new PdfPCell(new Paragraph("Class ID - " + classIDtxt.getText()));
		PdfPCell ri4 = new PdfPCell(new Paragraph("Questions - " + noQusetiontxt.getText()));
		PdfPCell ri5 = new PdfPCell(new Paragraph("Rlease Date - " + ReleaseDateTimetxt.getText()));
		PdfPCell ri6 = new PdfPCell(new Paragraph("Terminate Date - " + TerminateDateTimetxt.getText()));
		
		ri1.setBorder(Rectangle.NO_BORDER);
		ri2.setBorder(Rectangle.NO_BORDER);
		ri3.setBorder(Rectangle.NO_BORDER);
		ri4.setBorder(Rectangle.NO_BORDER);
		ri5.setBorder(Rectangle.NO_BORDER);
		ri6.setBorder(Rectangle.NO_BORDER);
		
		resultInfo.addCell(ri1);
		resultInfo.addCell(ri2);
		resultInfo.addCell(ri3);
		resultInfo.addCell(ri4);
		resultInfo.addCell(ri5);
		resultInfo.addCell(ri6);
		
		doc.add(resultInfo);
		doc.add(new Paragraph(" "));
		doc.add(new Paragraph(" "));
		
		PdfPTable resultTable = new PdfPTable(4);
		resultTable.setTotalWidth(new float[]{ 100, 180, 80, 180 });
		resultTable.setLockedWidth(true);
		
		PdfPCell cell1 = new PdfPCell(new Paragraph("Student ID",FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.WHITE)));
		PdfPCell cell2 = new PdfPCell(new Paragraph("Student Name",FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.WHITE)));
		PdfPCell cell3 = new PdfPCell(new Paragraph("Marks",FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.WHITE)));
		PdfPCell cell4 = new PdfPCell(new Paragraph("Date & Time",FontFactory.getFont(FontFactory.HELVETICA,14,BaseColor.WHITE)));
		
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBackgroundColor(BaseColor.DARK_GRAY);
		resultTable.addCell(cell1);
		
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell2.setBackgroundColor(BaseColor.DARK_GRAY);
		resultTable.addCell(cell2);
		
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell3.setBackgroundColor(BaseColor.DARK_GRAY);
		resultTable.addCell(cell3);
		
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell4.setBackgroundColor(BaseColor.DARK_GRAY);
		resultTable.addCell(cell4);
		
		
		TeacherViewResultsFunction tvrf2 = new TeacherViewResultsFunction();
		String resultsArray[][] = tvrf2.printResultList(paperIDtxt2.getText());
		int rowcount = resultsArray.length;
		for (int i =0; i<rowcount; i++)
		{
			resultTable.addCell(resultsArray[i][0]);
			resultTable.addCell(resultsArray[i][1]);
			resultTable.addCell(resultsArray[i][2]);
			resultTable.addCell(resultsArray[i][3]);
		}
	    doc.add(resultTable);
		doc.close();
		
		}
	
	}
	
	@FXML
	private void viewResultChart (MouseEvent event) throws Exception
	{
	
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/TeacherViewResultChartGUI.fxml"));
		
		Parent root = loader.load();
		//Stage stage = new Stage();
		//Parent root = FXMLLoader.load(getClass().getResource("/application/TeacherViewResultChartGUI.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
		
		TeacherViewResultChartController tvcc = loader.getController();
		tvcc.setBarChart(paperIDtxt2.getText());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TeacherDrawerController ad1 = new TeacherDrawerController();
		ad1.TeacherDrawer(Hamburger, Drawer);
		
		
			
	}
	
}
