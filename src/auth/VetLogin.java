package auth;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class VetLogin {

    public static String vetLogin() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("\n===== LOGIN =====");

            System.out.print("Username: ");
            String username = input.nextLine();

            JPasswordField passwordField = new JPasswordField();
            
            int option = JOptionPane.showConfirmDialog(
                    null,
                    passwordField,
                    "Password",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
            
            if (option != JOptionPane.OK_OPTION) {
                System.out.println("Login cancelled.");
                return null;
            }
            
            String password = new String(passwordField.getPassword());

            Connection con = DbConnection.getConnection();

            String sql = "SELECT user_id, role FROM users WHERE username = ? AND password = ?";

            PreparedStatement pst = con.prepareStatement(sql);

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
