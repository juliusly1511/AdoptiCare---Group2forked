package auth;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import util.PasswordHash;

public class VetLogin {

    public static String vetLogin() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("\n===== 👨‍⚕ VETERINARIAN LOGIN =====");

            String username, password;

            //=====================
            //CHECK USER VALIDATION
            //=====================
            while (true) {

                System.out.print("👤 Username: ");
                username = input.nextLine();

                if (username.isEmpty()) {
                    System.out.println("\n⚠ Username cannot be empty.");
                    System.out.println("👉 Please enter a username.\n");
                    continue;
                }

                if (username.contains(" ")) {
                    System.out.println("\n⚠ Username cannot contain spaces.");
                    System.out.println("👉 Use letters, numbers, or underscores (_).\n");
                    continue;
                }

                if (username.length() < 4) {
                    System.out.println("\n⚠ Username must be atleast 4 or more than letters!\n");
                    continue;
                }

                break;
            }

            //=========================
            //CHECK PASSWORD VALIDATION
            //=========================
            while (true) {

                System.out.print("🔒 Password: ");
                password = input.nextLine();

                if (password.isEmpty()) {
                    System.out.println("\n⚠ Password cannot be empty!\n");
                    System.out.println("👉 Please enter a password.\n");
                    continue;
                }

                if (password.contains(" ")) {
                    System.out.println("\n⚠ Password cannot contain spaces.");
                    System.out.println("👉 Use letters, numbers, or underscores (_).\n");
                    continue;
                }

                if (password.length() < 8) {
                    System.out.println("\n⚠ Password must be atleast 8 or more than letters!\n");
                    continue;
                }

                break;
            }

            Connection con = DbConnection.getConnection();

            // Passwords are BCrypt-hashed in DB, so we must verify using the stored hash
            String sql = "SELECT user_id, role, password FROM users WHERE username = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, username);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String storedHash = rs.getString("password");
                if (PasswordHash.verifyPassword(password, storedHash)) {
                    System.out.println("✅ Login Successfully!");
                    return rs.getString("role");
                }

                System.out.println("❌ Incorrect username or password.");
            } else {
                System.out.println("❌ Incorrect username or password.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        return null;
    }
}
