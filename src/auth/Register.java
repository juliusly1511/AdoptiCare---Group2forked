package auth;

import database.DBConnect;
import java.sql.*;
import java.util.Scanner;

public class Register {

    public static void registerCustomer() {
        
        Scanner input = new Scanner(System.in);
        
        try {
            
            System.out.println("\n===== REGISTER =====");
            
            System.out.println("Username: ");
            String username = input.nextLine();
            
            System.out.println("Password: ");
            String password = input.nextLine();
            
            Connection con = DBConnect.getConnection();
            
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
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
