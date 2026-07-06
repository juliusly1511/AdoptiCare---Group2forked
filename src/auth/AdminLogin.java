package auth;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class AdminLogin {

    public static String adminLogin() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("\n===== LOGIN =====");

            String username, password;
            
            //=====================
            //CHECK USER VALIDATION
            //=====================
            while (true) {

                System.out.print("Username: ");
                username = input.nextLine();

                if (username.isEmpty()) {
                    System.out.println("Username cannot be empty!");
                    continue;
                }

                if (username.contains(" ")) {
                    System.out.println("Username cannot contain spaces, use special characters(_).");
                    continue;
                }

                if (username.length() < 4) {
                    System.out.println("Username must be atleast 4 or more than letters!");
                    continue;
                }

                break;
            }

            //=========================
            //CHECK PASSWORD VALIDATION
            //=========================
            while (true) {

                System.out.print("Password: ");
                password = input.nextLine();

                if (username.isEmpty()) {
                    System.out.println("Password cannot be empty!");
                    continue;
                }

                if (username.contains(" ")) {
                    System.out.println("Password cannot contain spaces, use special characters(_).");
                    continue;
                }

                if (username.length() < 8) {
                    System.out.println("Password must be atleast 8 or more than letters!");
                    continue;
                }

                break;
            }

            Connection con = DbConnection.getConnection();

            String queryAdmin = "SELECT user_id, role FROM users WHERE username = ? AND password = ?";

            PreparedStatement pst = con.prepareStatement(queryAdmin);

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                System.out.println("Login Successfully!");

                return rs.getString("role");
            } else {
                System.out.println("Incorrect username or password.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
