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

            System.out.println("\n===== 📝 REGISTER =====");

            String username, password;

            while (true) {

                System.out.print("👤 Username: ");
                username = input.nextLine();

                //===============================================================
                //Data validation: restricted username to have spaces/whitespace
                //===============================================================
                if (!username.matches("\\S+")) {
                    System.out.println("\n⚠ Invalid username: No spaces allowed!");
                    continue;
                }

                //====================
                //Check minimum length
                //====================
                if (username.length() < 4) {
                    System.out.println("\n⚠ Username must be at least 4 characters long!");
                    continue;
                }

                if (username.trim().isEmpty()) {
                    System.out.println("\n⚠ Invalid username: Fill the username.");
                    continue;
                }

                String checkQuery = "SELECT username "
                        + "FROM users "
                        + "WHERE username = ?";

                PreparedStatement checkPst = con.prepareStatement(checkQuery);

                checkPst.setString(1, username);

                ResultSet rs = checkPst.executeQuery();

                if (rs.next()) {
                    System.out.println("\n❌ Username already exist!");
                    continue;
                }
                break;
            }

            while (true) {

                System.out.print("🔒 Password: ");
                password = input.nextLine();
                   //password.equals to password.matches
                if (!password.matches("\\S+")) {
                    System.out.println("\n⚠ Password cannot contain spaces!");
                    continue;
                }

                if (password.length() < 8) {
                    System.out.println("\n⚠ Password must be atleast 8 characters long!");
                    continue;
                }
                //!password.trim to password.trim - removed "!"
                if (password.trim().isEmpty()) {
                    System.out.println("\n⚠ Invalid username: Fill the username.");
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
                System.out.println("\n✅ Registration Successful!");
            }

            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
