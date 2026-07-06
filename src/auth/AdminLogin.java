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

            System.out.print("Username: ");
            String username = input.nextLine();
            
            System.out.println("Password: ");
            String password = input.nextLine();
            
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
