package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ViewPetDetails {

    public static void viewPetDetails(int petId) {

        Scanner input = new Scanner(System.in);

        try {
            
            System.out.println("\n===== PET DETAILS =====");

            Connection con = DbConnection.getConnection();

            String sql
                    = "SELECT p.*, pm.vaccine_name, pm.health_condition, "
                    + "pm.last_vaccination_date, pm.next_vaccination_schedule, "
                    + "pm.vaccination_status "
                    + "FROM pets p "
                    + "LEFT JOIN pet_medical_records pm "
                    + "ON p.pet_id = pm.pet_id "
                    + "WHERE p.archived = 0";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                
                System.out.println("\nPet ID: " + rs.getInt("pet_id"));

                System.out.println("Pet Name: " + rs.getString("pet_name"));

                System.out.println("Species: " + rs.getString("species"));

                System.out.println("Age: " + rs.getInt("age"));

                System.out.println("Gender: " + rs.getString("gender"));

                System.out.println("Breed: " + rs.getString("breed"));

                System.out.println("Description: " + rs.getString("description"));
                
                System.out.println("\n===== " + rs.getString("pet_name") + "MEDICAL RECORD =====");
                
                System.out.println("Vaccine Name: " + rs.getString("vaccine_name"));
                
                System.out.println("Health Condition: " + rs.getString("health_condition"));
                
                System.out.println("Last Vaccination Date: " + rs.getTimestamp("last_vaccination_schedule"));
                
                System.out.println("Next Vaccination Schedule: " + rs.getTimestamp("next_vaccination_schedule"));
                
                System.out.println("Vaccination Status: " + rs.getString("vaccination_status"));
                
                System.out.println("==============================");
            } else {
                System.out.println("Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
