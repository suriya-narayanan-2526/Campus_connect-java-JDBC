package campusconnect.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import campusconnect.db.DBconnection;

public class StudentLogin {

    public String login()   // return email instead of boolean
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Email Id : ");
        String email = sc.nextLine();
        System.out.print("Enter your Password: ");
        String password = sc.nextLine();

        try {
            Connection con = DBconnection.getConnection();
            String query = "select * from students where email=? and password=?"; 
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("\n✅ Login Successful! Welcome " + rs.getString("name") + "\n");
                return rs.getString("email");  
            } 
            else {
                System.out.println("\n❌ Invalid Credentials! Try Again.\n");
                return null;  
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
