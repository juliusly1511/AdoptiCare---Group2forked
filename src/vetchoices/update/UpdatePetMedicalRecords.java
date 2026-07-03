package vetchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class UpdatePetMedicalRecords {

    public static void updatePetMedicalRecords() {

        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Vaccination ID: ");
            int vaccinationId = input.nextInt();
            
            input.nextLine();
            
            System.out.println("Vaccination Name: ");
            String vaccinationName = input.nextLine();
            
            System.out.println("Health Condition: ");
            String healthCondition = input.nextLine();
            
            System.out.println("Last Vaccination Date (yyyy-MM-dd HH:mm:ss): ");
            String lastVaccination = input.nextLine();
            
            System.out.println("Next Vaccination Schedule (yyyy-MM-dd HH:mm:ss): ");
            String nextVaccination = input.nextLine();
            
            System.out.println("Vaccination Status: ");
            String vaccinationStatus = input.nextLine();
            
            Timestamp lastVaccinationDate = null;
            
            Timestamp nextVaccinationSchedule = null;

            if(!lastVaccination.trim().isEmpty()) {
                lastVaccinationDate = Timestamp.valueOf(lastVaccination);
            }
            
            if(!nextVaccination.trim().isEmpty()) {
                nextVaccinationSchedule = Timestamp.valueOf(nextVaccination);
            }
            
            Connection con = DbConnection.getConnection();

            String sql = "UPDATE pet_medical_records "
                    + "SET vaccine_name = ?, "
                    + "health_condition = ?, "
                    + "last_vaccination_date = ?, "
                    + "next_vaccination_schedule = ?, "
                    + "vaccination_status = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, vaccinationName);
            pst.setString(2, healthCondition);
            if (lastVaccinationDate != null) {
                pst.setTimestamp(3, lastVaccinationDate);
            } else {
                pst.setNull(3, java.sql.Types.TIMESTAMP);
            }
            
            if (nextVaccinationSchedule != null) {
                pst.setTimestamp(4, nextVaccinationSchedule);
            } else {
                pst.setNull(4, java.sql.Types.TIMESTAMP);
            }
            pst.setString(5, vaccinationStatus);
            pst.setInt(6, vaccinationId);

            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Updated Successfully!");
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    
    public static void updateVaccinationName() {
        
    }
}
