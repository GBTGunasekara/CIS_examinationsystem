package application;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;



public class SystemServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost:1099/Hello", new TeacherRegFunction());
			System.out.println("Hello Server Strarted");
		} catch (Exception e){
			System.out.println("Hello Server Failed");
		}
	}

}
