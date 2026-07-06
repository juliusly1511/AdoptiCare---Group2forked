package auth;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import util.PasswordHash;

public class Register {

    public static void registerCustomer() {

        Scanner input = new Scanner(System.in);

        try {
            Connection con = DbConnection.getConnection();

            System.out.println("\n===== REGISTER =====");

            String username, password;

            while (true) {

                System.out.print("Username: ");
                username = input.nextLine();

                //===============================================================
                //Data validation: restricted username to have spaces/whitespace
                //===============================================================
                if (!username.matches("\\S+")) {
                    System.out.println("\nInvalid username: No spaces allowed!");
                    continue;
                }

                //====================
                //Check minimum length
                //====================
                if (username.length() < 4) {
                    System.out.println("\nUsername must be at least 4 characters long!");
                    continue;
                }

                if (username.trim().isEmpty()) {
                    System.out.println("\nInvalid username: Fill the username.");
                    continue;
                }

                String checkQuery = "SELECT username "
                        + "FROM users "
                        + "WHERE username = ?";

                PreparedStatement checkPst = con.prepareStatement(checkQuery);

                checkPst.setString(1, username);

                ResultSet rs = checkPst.executeQuery();

                if (rs.next()) {
                    System.out.println("\nUsername already exist!");
                    continue;
                }
                break;
            }

            while (true) {

                System.out.print("Password: ");
                password = input.nextLine();

                if (!password.equals("\\S+")) {
                    System.out.println("Password cannot contain spaces!");
                    continue;
                }

                if (password.length() < 8) {
                    System.out.println("\nPassword must be atleast 8 characters long!");
                    continue;
                }

                if (!password.trim().isEmpty()) {
                    System.out.println("\nInvalid username: Fill the username.");
                    continue;
                }
                break;
            }
            String hashedPassword = PasswordHash.hashPassword(password);

            String sql
                    = "INSERT INTO users(username, password, role) "
                    + "VALUES (?, ?, 'Customer')";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, hashedPassword);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\nRegistration Successfully!");
            }

            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
