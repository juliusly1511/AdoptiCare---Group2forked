package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import util.AgeConverter;

public class ViewPetDetails {

    public static void viewPetDetails(int petId) {

        Scanner input = new Scanner(System.in);

        try {

            System.out.println("\n===== 🐾 PET DETAILS =====");

            Connection con = DbConnection.getConnection();

            String queryDetails
                    = "SELECT p.*, pm.vaccine_name, pm.health_condition, "
                    + "pm.last_vaccination_date, pm.next_vaccination_schedule, "
                    + "pm.vaccination_status, pm.diet, pm.vitamins "
                    + "FROM pets p "
                    + "LEFT JOIN pet_medical_records pm "
                    + "ON p.pet_id = pm.pet_id "
                    + "WHERE p.pet_id = ? AND p.archived = 0";

            PreparedStatement pst = con.prepareStatement(queryDetails);
            
            pst.setInt(1, petId);
            
            ResultSet rs = pst.executeQuery();

            //====================
            // PET DETAILS COLUMN
            //====================
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf(
                    "| %-6s | %-15s | %-8s | %-14s | %-10s | %-15s | %-30s | %-12s |%n",
                    "Pet ID",
                    "Pet Name",
                    "Gender",
                    "Age",
                    "Species",
                    "Breed",
                    "Description",
                    "Status"
            );

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            //=====================
            // PET DETAILS VALUE
            //=====================
            if (rs.next()) {

                System.out.printf(
                        "| %-6d | %-15s | %-8s | %-14s | %-10s | %-15s | %-30s | %-12s |%n",
                        rs.getInt("pet_id"),
                        rs.getString("pet_name"),
                        rs.getString("gender"),
                        AgeConverter.convertAge(rs.getDouble("age")),
                        rs.getString("species"),
                        rs.getString("breed"),
                        rs.getString("description"),
                        rs.getString("adoption_status")
                );

                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                //============================
                // PET MEDICAL RECORD COLUMN
                //============================
                System.out.println("\n===== " + rs.getString("pet_name") + " MEDICAL RECORD =====\n");

                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");

                System.out.printf(
                        "| %-15s | %-20s | %-20s | %-20s | %-20s | %-15s | %-15s |%n",
                        "Vaccine",
                        "Condition",
                        "Last Shot",
                        "Next Shot",
                        "Status",
                        "Diet",
                        "Vitamins"
                );

                System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");

                do {

                    System.out.printf(
                            "| %-15s | %-20s | %-20s | %-20s | %-20s | %-15s | %-15s |%n",
                            (rs.getString("vaccine_name") == null ? "N/A" : rs.getString("vaccine_name")),
                            (rs.getString("health_condition") == null ? "Not yet examined" : rs.getString("health_condition")),
                            (rs.getDate("last_vaccination_date") == null ? "N/A" : rs.getDate("last_vaccination_date")),
                            (rs.getDate("next_vaccination_schedule") == null ? "N/A" : rs.getDate("next_vaccination_schedule")),
                            (rs.getString("vaccination_status") == null ? "Not Vaccinated" : rs.getString("vaccination_status")),
                            (rs.getString("diet") == null ? "N/A" : rs.getString("diet")),
                            (rs.getString("vitamins") == null ? "N/A" : rs.getString("vitamins"))
                    );

                    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");

                } while (rs.next());
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
