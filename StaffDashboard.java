package campusconnect.staff;
import campusconnect.db.DBconnection;
import java.sql.*;
import java.util.Scanner;

public class StaffDashboard {

    static Scanner sc = new Scanner(System.in);

    public static void showDashboard() {
        int choice;

        do {
            System.out.println("\n===== STAFF DASHBOARD =====");
            System.out.println("1. Manage Students");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Enter Marks");
            System.out.println("4. Post Announcements");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    manageStudents();
                    break;
                case 2:
                    markAttendance();
                    break;
                case 3:
                    enterMarks();
                    break;
                case 4:
                    postAnnouncement();
                    break;
                case 5:
                    System.out.println("Logged out.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }

        } while (choice != 5);
    }

    // 1️⃣ Manage Students
    private static void manageStudents() {
        System.out.println("\n--- Manage Students ---");
        System.out.println("1. View Students");
        System.out.println("2. Add Student");
        System.out.println("3. Remove Student");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();
        sc.nextLine();

        try (Connection con = DBconnection.getConnection()) {

            if (ch == 1) {
                String query = "SELECT * FROM students";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next()) {
                    System.out.println(rs.getInt("student_id") + " - " + rs.getString("name") + " - " + rs.getString("department"));
                }
            }
            else if (ch == 2) {
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Email: ");
                String email = sc.nextLine();
                System.out.print("Enter Department: ");
                String dept = sc.nextLine();
                System.out.print("Enter Year: ");
                String year = sc.nextLine();
                System.out.print("Enter Phone: ");
                String phone = sc.nextLine();

                String query = "INSERT INTO students(name, email, department, year, phone) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, email);
                pst.setString(3, dept);
                pst.setString(4, year);
                pst.setString(5, phone);
                pst.executeUpdate();
                System.out.println("Student added successfully ✅");
            }
            else if (ch == 3) {
                System.out.print("Enter Student ID to remove: ");
                int id = sc.nextInt();
                String query = "DELETE FROM students WHERE student_id=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, id);
                pst.executeUpdate();
                System.out.println("Student removed ✅");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2️⃣ Mark Attendance
    private static void markAttendance() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Subject: ");
        String subject = sc.nextLine();
        System.out.print("Enter Status (Present/Absent): ");
        String status = sc.nextLine();

        try (Connection con = DBconnection.getConnection()) {
            String query = "INSERT INTO attendance(student_id, date, subject, status) VALUES (?, CURDATE(), ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, subject);
            pst.setString(3, status);
            pst.executeUpdate();
            System.out.println("Attendance marked ✅");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ Enter Marks
    private static void enterMarks() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Subject: ");
        String subject = sc.nextLine();
        System.out.print("Enter Internal 1 Marks: ");
        int i1 = sc.nextInt();
        System.out.print("Enter Internal 2 Marks: ");
        int i2 = sc.nextInt();
        System.out.print("Enter Semester Marks: ");
        int sem = sc.nextInt();

        try (Connection con = DBconnection.getConnection()) {
            String query = "INSERT INTO marks(student_id, subject, internal1, internal2, semester) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, subject);
            pst.setInt(3, i1);
            pst.setInt(4, i2);
            pst.setInt(5, sem);
            pst.executeUpdate();
            System.out.println("Marks recorded ✅");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4️⃣ Post Announcements
    private static void postAnnouncement() {
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Message: ");
        String message = sc.nextLine();

        try (Connection con = DBconnection.getConnection()) {
            String query = "INSERT INTO announcements(title, message) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, title);
            pst.setString(2, message);
            pst.executeUpdate();
            System.out.println("Announcement Posted ✅");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
