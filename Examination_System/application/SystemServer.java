package application;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;



public class SystemServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:1099/TeacherReg", new TeacherRegFunction());
			Naming.rebind("rmi://localhost:1099/Student", new StudentRegFunction());
			Naming.rebind("rmi://localhost:1099/Login", new LoginFunctions());
			Naming.rebind("rmi://localhost:1099/TeacherAddPaper", new TeacherAddPaperFunction());
			Naming.rebind("rmi://localhost:1099/TeacherAddQuestions", new TeacherAddQuestionsFunction());
			Naming.rebind("rmi://localhost:1099/TeacherSubmitPaper", new TeacherSubmitPaperFunction());
			Naming.rebind("rmi://localhost:1099/StudentSelectPaper", new StudentSelectPaperFunction());
			Naming.rebind("rmi://localhost:1099/StudentAnswerPaper", new StudentAnswerPaperFunction());
			Naming.rebind("rmi://localhost:1099/StudentResultPaper", new StudentResultPaperFunction());
			
			Naming.rebind("rmi://localhost:1099/TeacherAddClass", new TeacherAddClassFunction());
			Naming.rebind("rmi://localhost:1099/TeacherProfile", new TeacherProfileFunction());
			Naming.rebind("rmi://localhost:1099/StudentProfile", new StudentProfileFunction());
			
			System.out.println("MCQ app Server Strarted");
		} catch (Exception e){
			System.out.println("MCQ app Server Failed");
		}
	}

}
