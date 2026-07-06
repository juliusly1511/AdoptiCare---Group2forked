package auth;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class CustomerResetPassword {

    public static void resetPassword() {

        Scanner input = new Scanner(System.in);

        try {
            Connection con = DbConnection.getConnection();
            
            System.out.println("\n===== RESET PASSWORD =====");

            int userId = Login.loggedInUserId;

            if (userId == -1) {
                System.out.println("You are not logged in!");
                return;
            }

            //==========================================
            //GET CURRENT PASSWORD BY USING SELECT QUERY
            //==========================================
            String sql
                    = "SELECT password "
                    + "FROM users "
                    + "WHERE user_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, userId);
            
            ResultSet rs = pst.executeQuery();
            
            if(!rs.next()) {
                System.out.println("User not found!");
                return;
            }
            
            //=========================
            //ASK FOR CURRENT PASSWORD
            //=========================
            String storedHashedPassword = rs.getString("password");
            
            System.out.print("Enter current password: ");
            String currentPassword = input.nextLine();
            
            //=======================
            //VERIFY CURRENT PASSWORD
            //=======================
            if(!BCrypt.checkpw(currentPassword, storedHashedPassword)) {
                System.out.println("Invalid password: Your password does not match the current password!");
                return;
            }
            
            //==================
            //INPUT NEW PASSWORD
            //==================
            System.out.print("Enter new password: ");
            String newPassword = input.nextLine();
            
            System.out.println("Confirm new password: ");
            String confirmPassword = input.nextLine();
            
            if(!newPassword.equals(confirmPassword)) {
                System.out.println("Password does not match!");
                return;
            }
            
            //=================
            //HASH NEW PASSWORD
            //=================
            String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            
            //===============
            //UPDATE DATABASE
            //===============
            String updateSql = "UPDATE users "
                    + "SET password = ? "
                    + "WHERE user_id = ?";
            
            PreparedStatement updatePst = con.prepareStatement(updateSql);
            
            updatePst.setString(1, hashedNewPassword);
            updatePst.setInt(2, userId);
            
            int updated = updatePst.executeUpdate();
            
            if(updated > 0) {
                System.out.println("Password successfully changed!");
            } else {
                System.out.println("Failed to update password.");
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
