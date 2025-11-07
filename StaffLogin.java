package campusconnect.staff;
import java.util.Scanner;
import campusconnect.db.DBconnection;
import java.sql.*;

public class StaffLogin {
	public boolean login()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your Email Id : ");
		String email = sc.nextLine();
		System.out.print("Enter your Password: ");
		String password = sc.nextLine();
		try
		{
			Connection con =DBconnection.getConnection();
			String query ="select * from staff where email=? and password=?";
		    PreparedStatement pst = con.prepareStatement(query);
		    pst.setString(1, email);
		    pst.setString(2, password);
		    ResultSet rs =pst.executeQuery();
		    if(rs.next())
		    {
                System.out.println("\n✅ Login Successful! Welcome " + rs.getString("name") + "\n");
                return true;
		    }
		    else {
                System.out.println("\n❌ Invalid Credentials! Try Again.\n");
                return false;
            }		
		}
		catch(Exception e)
		{
            System.out.println("Error: " + e.getMessage());
            return false;
		}
	}

}
