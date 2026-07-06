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

            String queryDetails
                    = "SELECT p.*, pm.vaccine_name, pm.health_condition, "
                    + "pm.last_vaccination_date, pm.next_vaccination_schedule, "
                    + "pm.vaccination_status "
                    + "FROM pets p "
                    + "LEFT JOIN pet_medical_records pm "
                    + "ON p.pet_id = pm.pet_id "
                    + "WHERE p.archived = 0";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(queryDetails);

            System.out.printf(
                    "%-5s %-15s %-10s %-5s %-8s %-25s %-40s%n",
                    "ID",
                    "Pet Name",
                    "Species",
                    "Age",
                    "Gender",
                    "Breed",
                    "Description"
            );

            System.out.println("-----------------------------------------------------------------------------------------------");

            if (rs.next()) {

                System.out.printf(
                        "%-5d %-15s %-10s %-5d %-8s %-25s %-40s%n",
                        rs.getInt("pet_id"),
                        rs.getString("pet_name"),
                        rs.getString("species"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("breed"),
                        rs.getString("description")
                );

                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

                System.out.println("\n===== " + rs.getString("pet_name") + " MEDICAL RECORD =====\n");

                System.out.printf(
                        "%-15s %-20s %-20s %-20s %-15s %-20s %-20s%n",
                        "Vaccine",
                        "Condition",
                        "Last Shot",
                        "Next Shot",
                        "Status",
                        "Diet",
                        "Vitamins"
                );

                do {

                    System.out.printf(
                            "%-15s %-20s %-25s %-25s %-15s%n",
                            (rs.getString("vaccine_name") == null ? "N/A" : rs.getString("vaccine_name")),
                            (rs.getString("health_condition") == null ? "Not yet examined" : rs.getString("health_condition")),
                            (rs.getTimestamp("last_vaccination_date") == null ? "N/A" : rs.getTimestamp("last_vaccination_date")),
                            (rs.getTimestamp("next_vaccination_schedule") == null ? "N/A" : rs.getTimestamp("next_vaccination_schedule")),
                            (rs.getString("vaccination_status") == null ? "Not Vaccinated" : rs.getString("vaccination_status")),
                            (rs.getString("diet") == null ? "N/A" : rs.getString("diet")),
                            (rs.getString("vitamins") == null ? "N/A" : rs.getString("vitamins"))
                    );

                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

                } while (rs.next());
            } else {
                System.out.println("Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
