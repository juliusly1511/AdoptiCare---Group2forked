package auth;

import database.DbConnection;
import java.sql.*;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class Login {

    public static int loggedInUserId = -1;

    public static String login() {

        Scanner input = new Scanner(System.in);

        try {
            System.out.println("\n===== LOGIN =====");

            System.out.print("Username: ");
            String username = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql = "SELECT user_id, password, role FROM users WHERE username = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String hashedPassword = rs.getString("password");

                if (BCrypt.checkpw(password, hashedPassword)) {
                    loggedInUserId = rs.getInt("user_id");
                    
                    return rs.getString("role");
                } else {
                    System.out.println("Incorrect Password");
                }
                
            } else {
                System.out.println("Username not found.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }
}
