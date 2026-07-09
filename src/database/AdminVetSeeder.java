package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.PasswordHash;

public class AdminVetSeeder {

    public static void seedDefaultAccounts() {
        String[][] accounts = new String[][]{
            {"admin", "admin_1234", "Administrator"},
            {"veterinarian", "veterinarian_1234", "Veterinarian"}
        };

        try (Connection con = DbConnection.getConnection()) {
            for (String[] acc : accounts) {
                String username = acc[0];
                String plaintextPassword = acc[1];
                String role = acc[2];

                if (userExists(con, username)) {
                    continue;
                }

                String hashedPassword = PasswordHash.hashPassword(plaintextPassword);

                String sql = "INSERT INTO users(username, password, role, security_question, security_answer) "
                        + "VALUES (?, ?, ?, NULL, NULL)";

                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, username);
                    pst.setString(2, hashedPassword);
                    pst.setString(3, role);
                    pst.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error seeding admin/vet accounts: " + e.getMessage());
        }
    }

    private static boolean userExists(Connection con, String username) throws SQLException {
        String checkQuery = "SELECT user_id FROM users WHERE username = ?";
        try (PreparedStatement checkPst = con.prepareStatement(checkQuery)) {
            checkPst.setString(1, username);
            try (ResultSet rs = checkPst.executeQuery()) {
                return rs.next();
            }
        }
    }
}

