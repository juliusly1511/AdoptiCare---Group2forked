package vetchoices.create;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Scanner;
import util.ShowPetList;

public class AddPetMedicalRecords {

    public static void addPetMedicalRecords() {

        Scanner input = new Scanner(System.in);

        try {
            Connection con = DbConnection.getConnection();

            System.out.println("\n===== ➕ ADD PET MEDICAL RECORDS =====");

            ShowPetList.showPetVetList(con);

            System.out.print("\n🆔 Enter Pet ID (press 0 to cancel): ");
            int petId;

            while (true) {
                if (!input.hasNextInt()) {
                    System.out.println("\n⚠ Invalid input: Please enter a number.\n");
                    input.nextLine();
                    continue;
                }

                petId = input.nextInt();
                input.nextLine();

                if (petId == 0) {
                    System.out.println("\n↩ Returning to Veterinarian Menu...");
                    return;
                }

                break;
            }

            String sql = "INSERT INTO pet_medical_records "
                    + "(pet_id, vaccine_name, health_condition, last_vaccination_date, next_vaccination_schedule, vaccination_status, diet, vitamins) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);

            while (true) {

                //========================
                // MULTIPLE ADD METHOD
                //========================
                System.out.print("\nVaccine Name (type 'done' to finish): ");
                String vaccineName = input.nextLine();

                if (vaccineName.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.print("Health Condition: ");
                String healthCondition = input.nextLine();

                System.out.print("Vaccination Status: ");
                String status = input.nextLine();

                Date lastDate = null;
                Date nextSched = null;

                //==============================
                // NOT VACCINATED = NO DATE
                //==============================
                if (status.equalsIgnoreCase("Not Vaccinated")) {

                    lastDate = null;
                    nextSched = null;

                } else {

                    //=============================================
                    // PARTIALLY / FULLY VACCINATED NEED LAST DATE
                    //=============================================
                    System.out.print("Last Vaccination Date (yyyy-mm-dd): ");

                    while (true) {
                        try {

                            lastDate = Date.valueOf(input.nextLine());
                            break;

                        } catch (IllegalArgumentException e) {

                            System.out.print(
                                    "⚠ Invalid date format. Enter again (yyyy-mm-dd): "
                            );
                        }
                    }

                    //=============================================
                    // PARTIALLY VACCINATED = LAST DATE + 1 YEAR
                    //=============================================
                    if (status.equalsIgnoreCase("Partially Vaccinated")) {

                        LocalDate suggestedDate = lastDate.toLocalDate().plusYears(1);

                        nextSched = Date.valueOf(suggestedDate);

                        System.out.println("\n📅 Suggested Next Schedule: " + nextSched);

                        System.out.print("🗓 Enter Next Schedule " + "(Press [ENTER] to use suggested): ");
                        
                        String customNext = input.nextLine();
                        
                        if (!customNext.trim().isEmpty()) {
                            
                            nextSched = Date.valueOf(customNext);
                            
                        }

                        //=============================================
                        // FULLY VACCINATED = NO NEXT SCHEDULE
                        //=============================================
                    } else if (status.equalsIgnoreCase("Fully Vaccinated")) {

                        nextSched = null;
                    }
                }

                System.out.print("Diet: ");
                String diet = input.nextLine();

                System.out.print("Vitamins: ");
                String vitamins = input.nextLine();

                pst.setInt(1, petId);
                pst.setString(2, vaccineName);
                pst.setString(3, healthCondition);

                if (lastDate == null) {
                    pst.setNull(4, Types.DATE);
                } else {
                    pst.setDate(4, lastDate);
                }

                if (nextSched == null) {
                    pst.setNull(5, Types.DATE);
                } else {
                    pst.setDate(5, nextSched);
                }

                pst.setString(6, status);
                pst.setString(7, diet);
                pst.setString(8, vitamins);

                pst.executeUpdate();

                System.out.println("\n✅ Vaccine added successfully.");
                
            }

            System.out.println("\n✅ All vaccination records saved.");

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
