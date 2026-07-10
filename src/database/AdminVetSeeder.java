package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.PasswordHash;

public class AdminVetSeeder {

    public static void ensureAdminVetAccountsExist() {
        try (Connection con = DbConnection.getConnection()) {
            boolean hasAdmin = hasRole(con, "Administrator");
            boolean hasVet = hasRole(con, "Veterinarian");

            if (!hasAdmin) {
                System.out.println("\n⚠ Admin account (role: Administrator) not found in database.");
                createAccountInteractive(con, "Administrator");
            }

            if (!hasVet) {
                System.out.println("\n⚠ Veterinarian account (role: Veterinarian) not found in database.");
                createAccountInteractive(con, "Veterinarian");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error ensuring admin/vet accounts: " + e.getMessage());
        }
    }

    // Kept for backward compatibility; now delegates to ensureAdminVetAccountsExist
    public static void seedDefaultAccounts() {
        ensureAdminVetAccountsExist();
    }

    private static boolean hasRole(Connection con, String role) throws SQLException {
        String sql = "SELECT user_id FROM users WHERE role = ? LIMIT 1";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, role);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }

    private static void createAccountInteractive(Connection con, String role) throws SQLException {
        java.util.Scanner input = new java.util.Scanner(System.in);

        String username;
        String password;

        // Username validation
        while (true) {
            System.out.print(role.equals("Administrator") ? "👤 Admin Username: " : "👤 Vet Username: ");
            username = input.nextLine();

            if (username == null) username = "";
            username = username.trim();

            if (username.isEmpty()) {
                System.out.println("\n⚠ Username cannot be empty!");
                System.out.println("👉 Please enter a username.\n");
                continue;
            }
            if (username.contains(" ")) {
                System.out.println("\n⚠ Username cannot contain spaces.");
                System.out.println("👉 Use letters, numbers, or underscores (_).\n");
                continue;
            }
            if (username.length() < 4) {
                System.out.println("\n⚠ Username must be at least 4 characters long.\n");
                continue;
            }
            break;
        }

        // Password validation
        while (true) {
            System.out.print("🔒 " + (role.equals("Administrator") ? "Admin" : "Vet") + " Password: ");
            password = input.nextLine();

            if (password == null) password = "";

            if (password.isEmpty()) {
                System.out.println("\n⚠ Password cannot be empty!\n");
                continue;
            }
            if (password.contains(" ")) {
                System.out.println("\n⚠ Password cannot contain spaces.");
                System.out.println("👉 Use letters, numbers, or underscores (_).\n");
                continue;
            }
            if (password.length() < 8) {
                System.out.println("\n⚠ Password must be at least 8 characters long.\n");
                continue;
            }
            break;
        }

        // If username already exists, reject and ask again
        if (userExists(con, username)) {
            System.out.println("\n❌ That username already exists. Please restart creation for this account.");
            return;
        }

        String hashedPassword = PasswordHash.hashPassword(password);
        String sql = "INSERT INTO users(username, password, role, security_question, security_answer) "
                + "VALUES (?, ?, ?, NULL, NULL)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, hashedPassword);
            pst.setString(3, role);
            pst.executeUpdate();
        }

        System.out.println("\n✅ " + role + " account created successfully!");
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

    // (old seeding logic removed)
}


