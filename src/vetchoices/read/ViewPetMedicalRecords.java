package vetchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewPetMedicalRecords {

    public static void viewPetMedicalRecords() {

        try {

            System.out.println("\n===== PET MEDICAL RECORDS =====");

            Connection con = DbConnection.getConnection();

            String queryMed
                    = "SELECT * "
                    + "FROM pet_medical_records ";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(queryMed);

            //================
            //TABLE COLUMN
            //================
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf(
                    "| %-15s | %-8s | %-20s | %-20s | %-25s | %-25s | %-20s | %-15s | %-15s |%n",
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

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (!rs.next()) {
                System.out.println("\n❌ No medical records found.");
                return;
            }

            //=============
            // ROWS
            //=============
            do {

                System.out.printf(
                        "| %-15d | %-8d | %-20s | %-20s | %-25s | %-25s | %-20s | %-15s | %-15s |%n",
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

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            } while (rs.next());

            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
