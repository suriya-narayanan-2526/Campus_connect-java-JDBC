package campusconnectmain;
import java.util.Scanner;
import campusconnect.staff.StaffLogin;
import campusconnect.staff.StaffDashboard;
import campusconnect.student.StudentDashboard;
import campusconnect.student.StudentLogin;
public class CampusconnectApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		 StaffLogin staffLogin = new StaffLogin();
		 StudentLogin studentLogin = new StudentLogin();
		 while(true)
		 {
			 System.out.println("===== Campus Connect =====");
	         System.out.println("1. Staff Login");
	         System.out.println("2. Student Login");
	         System.out.println("3. Exit");
	         System.out.print("Enter Choice: ");

	         int choice = sc.nextInt();

	         switch (choice) {
	             case 1:
	                 if (staffLogin.login()) {
	                	 StaffDashboard.showDashboard();
	                 }
	                 break;
	             case 2:
	            	    String studentEmail = studentLogin.login(); 
	            	    if(studentEmail != null)   
	            	    {
	            	        StudentDashboard.showDashboard(studentEmail); 
	            	    }
	            	    else
	            	    {
	            	        System.out.println("Login Failed! Try Again.");
	            	    }
	            	    break;
	             case 3:
	                 System.out.println("Thank You!");
	                 System.exit(0);
	             default:
	                 System.out.println("Invalid Choice.\n");
		 }            

	}

}
}
