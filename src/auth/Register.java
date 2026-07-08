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
            
            //=======================
            // USERNAME VALIDATION
            //=======================
            while (true) {

                System.out.print("👤 Username: ");
                username = input.nextLine().trim();

                //===============================================================
                //Data validation: restricted username to have spaces/whitespace
                //===============================================================
                if (!username.matches("\\S+")) {
                    System.out.println("\n⚠ Invalid username: No spaces allowed!");
                    System.out.println("👉 Use letters, numbers, or underscores (_).\n");
                    continue;
                }

                //====================
                //Check minimum length
                //====================
                if (username.length() < 4) {
                    System.out.println("\n⚠ Username must be at least 4 characters long!\n");
                    continue;
                }

                if (username.trim().isEmpty()) {
                    System.out.println("\n⚠ Invalid username: Please enter a username.\n");
                    continue;
                }

                String checkQuery = "SELECT username "
                        + "FROM users "
                        + "WHERE username = ?";

                PreparedStatement checkPst = con.prepareStatement(checkQuery);

                checkPst.setString(1, username);

                ResultSet rs = checkPst.executeQuery();

                if (rs.next()) {
                    System.out.println("\n❌ Username already exist!\n");
                    continue;
                }
                break;
            }

            //==========================
            // PASSWORD VALIDATION
            //==========================
            while (true) {

                System.out.print("🔒 Password: ");
                password = input.nextLine();
                   //password.equals to password.matches
                if (!password.matches("\\S+")) {
                    System.out.println("\n⚠ Password cannot contain spaces!");


                if (!password.matches("\\S+")) {
                    System.out.println("\n⚠ Password cannot contain spaces!\n");
                    System.out.println("👉 Use letters, numbers, or underscores (_).\n");

                    continue;
                }

                if (password.length() < 8) {
                    System.out.println("\n⚠ Password must be atleast 8 characters!\n");
                    continue;
                }
                //!password.trim to password.trim - removed "!"
                if (password.trim().isEmpty()) {
                    System.out.println("\n⚠ Invalid username: Fill the username.");


                if (password.trim().isEmpty()) {
                    System.out.println("\n⚠ Invalid password: Please enter a password.\n");

                    continue;
                }
                break;
            }
            
            //=====================
            // SECURITY QUESTION
            //=====================
            System.out.println("\n🔐 Choose a Security Question");
            System.out.println("[1] What is your favorite pet's name?");
            System.out.println("[2] What is your favorite color?");
            System.out.println("[3] What city were you born in?");
            System.out.println("[4] What was your first school's name?");
            System.out.println("[5] What is your dream job?");
            
            String securityQuestion = "";
            
            while (true) {
                
                System.out.print("\n👉 Choose an option [1-5]: ");
                
                if (!input.hasNextInt()) {
                    System.out.println("\n❌ Invalid input.\n");
                    input.nextLine();
                    continue;
                }
                
                int choice = input.nextInt();
                input.nextLine();
                
                switch (choice) {
                    
                    case 1:
                        
                        securityQuestion = "What is your favorite pet's name?";
                        break;
                        
                    case 2:
                        securityQuestion = "What is your favorite color?";
                        break;
                        
                    case 3:
                        securityQuestion = "What city were you born in?";
                        break;
                        
                    case 4:
                        securityQuestion = "What was your first school's name?";
                        break;
                        
                    case 5:
                        securityQuestion = "What is your dream job?";
                        break;
                        
                    default:
                        System.out.println("\n❌ Please choose only from 1 to 5.\n");
                        continue;
                }
                break;
            }
            
            //========================
            // SECURITY ANSWER
            //========================
            
            String securityAnswer;
            
            while (true) {
                
                System.out.print("💬 Answer: ");
                securityAnswer = input.nextLine().trim();
                
                if (securityAnswer.isEmpty()) {
                    System.out.println("\n❌ Answer cannot be empty.\n");
                    continue;
                }
                
                break;
            }
            
            //====================
            // HASH PASSWORD
            //====================
            String hashedPassword = PasswordHash.hashPassword(password);
            
            //=======================
            // HASH SECURITY ANSWER
            //=======================
            String hashedAnswer = PasswordHash.hashPassword(securityAnswer);
            
            //====================
            // INSERT USER
            //====================
            String sql
                    = "INSERT INTO users(username, password, role, security_question, security_answer) "
                    + "VALUES (?, ?, 'Customer', ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, hashedPassword);
            pst.setString(3, securityQuestion);
            pst.setString(4, hashedAnswer);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Registration Successful!");
            } else {
                System.out.println("\n❌ Registration Failed.");
            }

            pst.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
