package vetchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewPetMedicalRecords {

    public static void viewPetMedicalRecords() {
        
        try {
            
            System.out.println("\nViewing Pet Medical Records");
            
            Connection con = DbConnection.getConnection();
            
            String queryMed = 
                    "SELECT * "
                    + "FROM pet_medical_records ";
            
            Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery(queryMed);
            
            System.out.println("\n===== Viewing Pet Medical Records =====");
            
            if (rs.next()) {
                System.out.println("\nVaccination ID: " + rs.getInt("vaccination_id"));
                
                System.out.println("Pet_ID: " + rs.getInt("pet_id")); 
                
                System.out.println("Vaccine Name: " + rs.getString("vaccine_name")); 
                
                System.out.println("Health Condition: " + rs.getString("health_condition"));
                
                System.out.println("Last Vaccination Date: " + rs.getTimestamp("last_vaccination_date"));
                
                System.out.println("Next Vaccination Schedule: " + rs.getTimestamp("next_vaccination_schedule")); 
                
                System.out.println("Vaccination Status: " + rs.getString("vaccination_status"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }
}
