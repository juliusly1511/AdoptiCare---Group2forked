package vetchoices.create;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Scanner;

public class AddVaccination {

    public static void addVaccination() {
        
        Scanner input = new Scanner(System.in);
        
        try {
            
            System.out.println("\nPet ID: ");
            int petId = input.nextInt();
            
            System.out.println("Vaccine Name: ");
            String vaccineName = input.nextLine();
            
            System.out.println("Last Vaccination Date (yyyy-mm-dd hh:mm:ss): ");
            String last_vaccination = input.nextLine();
            
            System.out.println("Next Vaccination Schedule (yyyy-mm-dd hh:mm:ss): ");
            String next_vaccination = input.nextLine();
            
            Timestamp lastVaccinationDate = Timestamp.valueOf(last_vaccination);
            Timestamp nextVaccinationSchedule = Timestamp.valueOf(next_vaccination);
            
            Connection con = DbConnection.getConnection();
            
            String sql = "INSERT INTO vaccinations "
                    + "(pet_id, vaccine_name, last_vaccination_date, next_vaccination_schedule) "
                    + "VALUES (?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, petId);
            pst.setString(2, vaccineName);
            pst.setTimestamp(3, lastVaccinationDate);
            pst.setTimestamp(4, nextVaccinationSchedule);
            
            int rows = pst.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Added Successfully!");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
