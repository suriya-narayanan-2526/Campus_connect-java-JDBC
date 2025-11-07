package campusconnect.student;

import campusconnect.db.DBconnection;
import java.sql.*;
import java.util.Scanner;

public class StudentDashboard {

    static Scanner sc = new Scanner(System.in);

    public static void showDashboard(String studentEmail) {
        int choice;

        do {
            System.out.println("\n===== STUDENT DASHBOARD =====");
            System.out.println("1. View Profile");
            System.out.println("2. View Attendance");
            System.out.println("3. View Marks");
            System.out.println("4. View Announcements");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewProfile(studentEmail);
                    break;
                case 2:
                    viewAttendance(studentEmail);
                    break;
                case 3:
                    viewMarks(studentEmail);
                    break;
                case 4:
                    viewAnnouncements();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 5);
    }

    // 1️⃣ View Profile
    private static void viewProfile(String email) {
        try (Connection con = DBconnection.getConnection()) {
            String q = "SELECT * FROM students WHERE email=?";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- PROFILE ---");
                System.out.println("Name       : " + rs.getString("name"));
                System.out.println("Email      : " + rs.getString("email"));
                System.out.println("Email      : " + rs.getString("password"));
                System.out.println("Department : " + rs.getString("department"));
                System.out.println("Year       : " + rs.getString("year"));
                System.out.println("Phone      : " + rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Helper to get student_id from email
    private static int getStudentId(String email) throws Exception {
        Connection con = DBconnection.getConnection();
        String q = "SELECT student_id FROM students WHERE email=?";
        PreparedStatement pst = con.prepareStatement(q);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getInt("student_id");
    }

    // 2️⃣ View Attendance
    private static void viewAttendance(String email) {
        try (Connection con = DBconnection.getConnection()) {
            int id = getStudentId(email);

            String q = "SELECT date, subject, status FROM attendance WHERE student_id=?";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            System.out.println("\n--- ATTENDANCE ---");
            while (rs.next()) {
                System.out.println(rs.getDate("date") + " | " + rs.getString("subject") + " | " + rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3️⃣ View Marks
    private static void viewMarks(String email) {
        try (Connection con = DBconnection.getConnection()) {
            int id = getStudentId(email);

            String q = "SELECT subject, internal1, internal2, semester FROM marks WHERE student_id=?";
            PreparedStatement pst = con.prepareStatement(q);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            System.out.println("\n--- MARKS ---");
            while (rs.next()) {
                System.out.println("Subject: " + rs.getString("subject"));
                System.out.println("Internal 1: " + rs.getInt("internal1"));
                System.out.println("Internal 2: " + rs.getInt("internal2"));
                System.out.println("Semester: " + rs.getInt("semester"));
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4️⃣ View Announcements
    private static void viewAnnouncements() {
        try (Connection con = DBconnection.getConnection()) {
            String q = "SELECT title, message, date FROM announcements ORDER BY date DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);

            System.out.println("\n--- ANNOUNCEMENTS ---");
            while (rs.next()) {
                System.out.println("Title : " + rs.getString("title"));
                System.out.println("Message : " + rs.getString("message"));
                System.out.println("Date : " + rs.getTimestamp("date"));
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

