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
            
            System.out.println("\n===== 🔐 FORGOT PASSWORD =====");

            //===========================
            // ENTER USERNAME
            //===========================
            String username;
            
            while (true) {
                System.out.println("👤 Enter Username: ");
                username = input.nextLine().trim();
                
                if (username.isEmpty()) {
                    System.out.println("\n❌ Username cannot be empty.\n");
                    continue;
                }
                
                break;
            }
            
            //==========================================
            //GET USER INFORMATION
            //==========================================
            String sql
                    = "SELECT user_id, security_question, security_answer "
                    + "FROM users "
                    + "WHERE username = ? AND role = 'Customer'";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, username);
            
            ResultSet rs = pst.executeQuery();
            
            if(!rs.next()) {
                System.out.println("\n❌ Username not found!");
                con.close();
                return;
            }
            
            //===========================================
            // GETTING USERID OF THE SELECTED USERNAME
            //===========================================
            int userId = rs.getInt("user_id");
            
            String securityQuestion = rs.getString("security_question");
            String storedAnswer = rs.getString("security_answer");
            
            //========================
            // ASK SECURITY QUESTION
            //========================
            System.out.println("\n❓ Security Question: ");
            System.out.println(securityQuestion);
            
            System.out.print("💬 Answer: ");
            String answer = input.nextLine().trim();
            
            //===================================
            // IF SECURITY ANSWER IS NOT MATCH 
            //===================================
            
            if (!BCrypt.checkpw(answer, storedAnswer)) {
                System.out.println("\n❌ Incorrect security answer.");
                con.close();
                return;
            }
            
            //==================
            // NEW PASSWORD
            //==================
            String newPassword;
            
            while (true) {
                System.out.println("🆕 Enter New Password: ");
                newPassword = input.nextLine();
                
                if (newPassword.isEmpty()) {
                    System.out.println("\n⚠ Password cannot be empty.\n");
                    continue;
                }
                
                if (newPassword.contains(" ")) {
                    System.out.println("\n⚠ Password cannot contain spaces.\n");
                    continue;
                }
                
                if (newPassword.length() < 8) {
                    System.out.println("\n⚠ Password must be at least 8 characters.\n");
                    continue;
                }
                
                break;
            }
            
            //========================
            // CONFIRM PASSWORD
            //========================
            
            while (true) {
                System.out.println("🔁 Confirm New Password: ");
                String confirmPassword = input.nextLine();
                
                if (!newPassword.equals(confirmPassword)) {
                    System.out.println("\n⚠Password do not match. Please try again.\n");
                    continue;
                }
                
                break;
            }
            
            //=================
            //HASH NEW PASSWORD
            //=================
            String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            
            //=============================
            //UPDATE PASSWORD ON DATABASE
            //=============================
            String updateSql = "UPDATE users "
                    + "SET password = ? "
                    + "WHERE user_id = ?";
            
            PreparedStatement updatePst = con.prepareStatement(updateSql);
            
            updatePst.setString(1, hashedNewPassword);
            updatePst.setInt(2, userId);
            
            int updated = updatePst.executeUpdate();
            
            if(updated > 0) {
                System.out.println("\n✅ Password reset successfully!");
            } else {
                System.out.println("\n❌ Failed to reset password.");
            }
            
            rs.close();
            pst.close();
            updatePst.close();
            con.close();
            
        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
