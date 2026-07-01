package adminchoices.update;

import database.DbConnection;
import java.sql.*;
import java.util.Scanner;

public class UpdatePet {

    public static void updatePet() {
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("===== UPDATE PET DETAILS =====");
        
        try {
            
            System.out.println("Enter Pet ID: ");
            int petId = input.nextInt();
            
            input.nextLine();
            
            System.out.print("Pet Name: ");
            String name = input.nextLine();
            
            System.out.print("Species: ");
            String species = input.nextLine();
            
            System.out.print("Age: ");
            int age = input.nextInt();
            
            System.out.print("Gender: ");
            String gender = input.nextLine();
            
            System.out.print("Breed: ");
            String breed = input.nextLine();
            
            System.out.print("Health Condition: ");
            String healthCondition = input.nextLine();
            
            System.out.print("Vaccination Status: ");
            String vaccinationStatus = input.nextLine();
            
            System.out.println("");
            
            input.nextLine();
            
            Connection con = DbConnection.getConnection();
            
            String sql = 
                    "UPDATE pets"
                    + "SET pet_name = ?,"
                    + "breed = ?"
                    + "age = ?"
                    + "WHERE pet_id = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, name);
            pst.setString(2, breed);
            pst.setInt(3, age);
            pst.setInt(4, petId);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Pet updated.");
            } else {
                System.out.println("Pet not found.");
            }
            
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
