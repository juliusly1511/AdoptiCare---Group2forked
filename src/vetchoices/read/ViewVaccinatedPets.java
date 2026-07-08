package vetchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class ViewVaccinatedPets {

    public static void viewVaccinatedPets() {

        Scanner input = new Scanner(System.in);

        try {

            Connection con = DbConnection.getConnection();

            String sql = "SELECT * "
                    + "FROM pet_medical_records "
                    + "WHERE vaccination_status = 'Fully Vaccinated'";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf(
                    "| %-15s | %-8s | %-20s | %-20s | %-25s | %-25s | %-15s | %-15s | %-15s%n |",
                    "Vaccination ID",
                    "Pet ID",
                    "Vaccine Name",
                    "Health Condition",
                    "Last Vaccination",
                    "Next Schedule",
                    "Status",
                    "Diet",
                    "Vitamins"
            );

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

                    System.out.printf(
                            "| %-15d | %-8d | %-20s | %-20s | %-25s | %-25s | %-15s | %-15s | %-15s%n |",
                            rs.getInt("vaccination_id"),
                            rs.getInt("pet_id"),
                            rs.getString("vaccine_name"),
                            rs.getString("health_condition"),
                            String.valueOf(rs.getDate("last_vaccination_date")),
                            String.valueOf(rs.getDate("next_vaccination_schedule")),
                            rs.getString("vaccination_status"),
                            rs.getString("diet"),
                            rs.getString("vitamins")
                    );

                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                } while (rs.next());

            } else {
                System.out.println("\n❌ Vaccination status not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

}
