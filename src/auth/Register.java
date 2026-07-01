package auth;

import database.DbConnection;
import java.sql.*;
import java.util.Scanner;

public class Register {

    public static void registerCustomer() {
        
        Scanner input = new Scanner(System.in);
        
        try {
            
            System.out.println("\n===== REGISTER =====");
            
            System.out.print("Username: ");
            String username = input.nextLine();
            
            System.out.print("Password: ");
            String password = input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String sql = 
                    "INSERT INTO users(username, password, role) VALUES (?, ?, ?)"; 
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, "Customer");
            
            int rows = pst.executeUpdate();
            
            if(rows > 0) {
                System.out.println("Registration Successfully!");
            }
            
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
