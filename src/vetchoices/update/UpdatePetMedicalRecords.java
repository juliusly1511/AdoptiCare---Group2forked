package vetchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import java.sql.Date;
import java.time.LocalDate;
import util.ShowPetList;

public class UpdatePetMedicalRecords {

    static Scanner input = new Scanner(System.in);

    // ================= MENU =================
    public static void updatePetMedicalRecordsMenu() {

        int menu;

        do {

            System.out.println("\n===== 🏥 UPDATE PET MEDICAL RECORDS =====");
            System.out.println("\n⚙ What would you like to update?");
            System.out.println("[1] 📝 Update Full Vaccination Record");
            System.out.println("[2] 💉 Update Vaccine Name");
            System.out.println("[3] ❤ Update Health Condition");
            System.out.println("[4] 📅 Update Last Vaccination Date");
            System.out.println("[5] 🗓 Update Next Vaccination Schedule");
            System.out.println("[6] 📌 Update Vaccination Status");
            System.out.println("[7] 🍽 Update Diet");
            System.out.println("[8] 💊 Update Vitamins");
            System.out.println("[9] ↩ Back");

            System.out.print("\n👉 Choose an option [1-9]: ");
            menu = input.nextInt();
            input.nextLine();

            switch (menu) {
                case 1:
                    updateFullRecord();
                    break;

                case 2:
                    updateVaccinationName();
                    break;

                case 3:
                    updateHealthCondition();
                    break;

                case 4:
                    updateLastVaccinationDate();
                    break;

                case 5:
                    updateNextVaccinationSchedule();
                    break;

                case 6:
                    updateVaccinationStatus();
                    break;

                case 7:
                    updateDiet();
                    break;

                case 8:
                    updateVitamins();
                    break;

                case 9:
                    System.out.println("\n↩ Going back...");
                    break;

                default:
                    System.out.println("\n❌ Invalid option.");
            }

        } while (menu != 9);
    }

    //=================
    // SHOW RECORDS 
    //=================
    private static void showVaccinations(Connection con, int petId) throws SQLException {

        String sql = "SELECT * FROM pet_medical_records WHERE pet_id=?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, petId);

        ResultSet rs = pst.executeQuery();

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

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

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        while (rs.next()) {

            String last = String.valueOf(rs.getDate("last_vaccination_date"));
            String next = String.valueOf(rs.getDate("next_vaccination_schedule"));

            System.out.printf(
                    "| %-15d | %-8d | %-20s | %-20s | %-25s | %-25s | %-20s | %-15s | %-15s |%n",
                    rs.getInt("vaccination_id"),
                    rs.getInt("pet_id"),
                    rs.getString("vaccine_name"),
                    rs.getString("health_condition"),
                    last,
                    next,
                    rs.getString("vaccination_status"),
                    rs.getString("diet"),
                    rs.getString("vitamins")
            );

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        }

        rs.close();
        pst.close();
    }

    // ================= EXISTS CHECK =================
    private static boolean exists(Connection con, int vaccinationId) throws SQLException {

        String sql = "SELECT 1 FROM pet_medical_records WHERE vaccination_id= ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, vaccinationId);

        ResultSet rs = pst.executeQuery();

        boolean found = rs.next();

        rs.close();
        pst.close();

        return found;
    }

    //===================
    // FULL UPDATE
    //===================
    public static void updateFullRecord() {

        try {

            Connection con = DbConnection.getConnection();

            ShowPetList.showPetVetList(con);

            System.out.println("\n===== 📝 UPDATE FULL RECORD =====");

            System.out.print("🆔 Enter Pet ID (press [0] to cancel): ");
            int petId = input.nextInt();

            input.nextLine();

            if (petId == 0) {
                System.out.println("↩ Returning to Veterinarian Menu...");
                return;
            }

            showVaccinations(con, petId);

            System.out.print("🆔 Enter Vaccination ID: ");
            int vaccinationId = input.nextInt();

            input.nextLine();

            if (!exists(con, vaccinationId)) {
                System.out.println("Invalid Vaccination ID.");
                return;
            }

            System.out.print("💉 Vaccine Name: ");
            String vaccineName = input.nextLine();

            System.out.print("❤ Health Condition: ");
            String health = input.nextLine();

            System.out.print("📌 Vaccination Status (Not Vaccinated / Partially Vaccinated / Fully Vaccinated): ");
            String status = input.nextLine();

            Date lastVaccination = null;
            Date nextVaccination = null;

            if (status.equalsIgnoreCase("Not Vaccinated")) {

                lastVaccination = null;
                nextVaccination = null;

            } else if (status.equalsIgnoreCase("Partially Vaccinated")) {

                //=========================
                // LAST VACCINATION DATE
                //=========================
                while (true) {

                    try {

                        System.out.print("📅 Last Vaccination Date (yyyy-MM-dd): ");
                        lastVaccination = Date.valueOf(input.nextLine());
                        break;

                    } catch (IllegalArgumentException e) {

                        System.out.println("\n⚠ Invalid date format. Please use yyyy-MM-dd.");

                    }

                }

                //=========================
                // SUGGEST NEXT SCHEDULE
                //=========================
                nextVaccination = Date.valueOf(lastVaccination.toLocalDate().plusYears(1));

                System.out.println("📅 Suggested Next Vaccination Schedule: " + nextVaccination);

                System.out.print("🗓 Enter Next Vaccination Schedule (Press ENTER to use suggested): ");
                String customNext = input.nextLine();

                if (!customNext.trim().isEmpty()) {
                    nextVaccination = Date.valueOf(customNext);
                }

            } else if (status.equalsIgnoreCase("Fully Vaccinated")) {

                //=========================
                // COMPLETED VACCINATION DATE
                //=========================
                while (true) {

                    try {

                        System.out.print("📅 Last Vaccination Date (yyyy-MM-dd): ");
                        lastVaccination = Date.valueOf(input.nextLine());
                        break;

                    } catch (IllegalArgumentException e) {

                        System.out.println("\n⚠ Invalid date format. Please use yyyy-MM-dd.");

                    }

                }

                // Fully vaccinated has no next schedule
                nextVaccination = null;

            } else {

                System.out.println("\n❌ Invalid Vaccination Status.");
                con.close();
                return;

            }

            System.out.print("🍽 Diet: ");
            String diet = input.nextLine();

            System.out.print("💊 Vitamins: ");
            String vitamins = input.nextLine();

            String sql = "UPDATE pet_medical_records SET "
                    + "vaccine_name=?, health_condition=?, last_vaccination_date=?, "
                    + "next_vaccination_schedule=?, vaccination_status=?, diet=?, vitamins=? "
                    + "WHERE vaccination_id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, vaccineName);
            pst.setString(2, health);

            if (lastVaccination == null) {
                pst.setNull(3, Types.DATE);
            } else {
                pst.setDate(3, lastVaccination);
            }

            if (nextVaccination == null) {
                pst.setNull(4, Types.DATE);
            } else {
                pst.setDate(4, nextVaccination);
            }

            pst.setString(5, status);
            pst.setString(6, diet);
            pst.setString(7, vitamins);
            pst.setInt(8, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {

                System.out.println("\n✅ Full record updated successfully!");

                if (lastVaccination == null) {
                    System.out.println("📅 Last Vaccination Date : NULL");
                } else {
                    System.out.println("📅 Last Vaccination Date : " + lastVaccination);
                }

                if (nextVaccination == null) {
                    System.out.println("📅 Next Vaccination Schedule : NULL");
                } else {
                    System.out.println("📅 Next Vaccination Schedule : " + nextVaccination);
                }

            } else {

                System.out.println("\n❌ Update failed.");

            }

            pst.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    // ================= SINGLE FIELD UPDATES =================
    public static void updateVaccinationName() {

        System.out.println("\n===== 💉 UPDATE VACCINATION NAME =====");

        updateField("vaccine_name", "💉 Vaccination Name");
    }

    public static void updateHealthCondition() {

        System.out.println("\n===== ❤ UPDATE HEALTH CONDITION =====");

        updateField("health_condition", "❤ Health Condition");
    }

    public static void updateDiet() {

        System.out.println("\n===== 🍽 UPDATE DIET =====");

        updateField("diet", "🍽 Diet");
    }

    public static void updateVitamins() {

        System.out.println("\n===== 💊 UPDATE VITAMINS =====");

        updateField("vitamins", "💊 Vitamins");
    }

    public static void updateField(String column, String label) {

        try {

            Connection con = DbConnection.getConnection();

            ShowPetList.showPetVetList(con);

            System.out.print("\n🆔 Enter Pet ID (press [0] to cancel): ");
            int petId = input.nextInt();

            input.nextLine();

            if (petId == 0) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }

            showVaccinations(con, petId);

            System.out.print("🆔 Vaccination ID: ");
            int id = input.nextInt();

            input.nextLine();

            System.out.print(label + ": ");
            String value = input.nextLine();

            String sql = "UPDATE pet_medical_records SET " + column + "= ? WHERE vaccination_id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, value);
            pst.setInt(2, id);

            pst.executeUpdate();

            System.out.println("\n✅ Updated successfully!");

            pst.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    public static void updateVaccinationStatus() {

        try {

            Connection con = DbConnection.getConnection();

            ShowPetList.showPetVetList(con);

            System.out.println("\n===== 📌 UPDATE VACCINATION STATUS =====");

            System.out.print("🆔 Enter Pet ID (press [0] to cancel: ");
            int petId = input.nextInt();

            input.nextLine();

            if (petId == 0) {
                System.out.println("↩ Returning to Veterinarian Menu...");
                return;
            }

            showVaccinations(con, petId);

            System.out.print("🆔 Vaccination ID: ");
            int vaccinationId = input.nextInt();
            input.nextLine();

            if (!exists(con, vaccinationId)) {
                System.out.println("\n❌ Invalid Vaccination ID.");
                return;
            }

            System.out.print("📌 New Vaccination Status (Not Vaccinated / Partially Vaccinated / Fully Vaccinated): ");
            String status = input.nextLine();

            // Get current last vaccination date
            String select = "SELECT last_vaccination_date, next_vaccination_schedule "
                    + "FROM pet_medical_records "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pstSelect = con.prepareStatement(select);
            pstSelect.setInt(1, vaccinationId);

            ResultSet rs = pstSelect.executeQuery();

            Date lastDate = null;
            Date currentNextSchedule = null;

            if (rs.next()) {
                lastDate = rs.getDate("last_vaccination_date");
                currentNextSchedule = rs.getDate("next_vaccination_schedule");
            }

            rs.close();
            pstSelect.close();

            Date nextSchedule = null;

            if (status.equalsIgnoreCase("Not Vaccinated")) {

                lastDate = null;
                nextSchedule = null;

            } else if (status.equalsIgnoreCase("Partially Vaccinated")) {

                if (lastDate == null) {
                    System.out.println("\n⚠ This record has no Last Vaccination Date.");
                    System.out.println("Please update the Last Vaccination Date first.");
                    con.close();
                    return;
                }

                LocalDate nextDate = lastDate.toLocalDate().plusYears(1);
                nextSchedule = Date.valueOf(nextDate);

            } else if (status.equalsIgnoreCase("Fully Vaccinated")) {

                if (currentNextSchedule == null) {
                    System.out.println("\n⚠ This record has no scheduled vaccination.");
                    System.out.println("Please update the Next Vaccination Schedule first.");
                    con.close();
                    return;
                }

                // The scheduled vaccination has now been completed.
                lastDate = currentNextSchedule;
                nextSchedule = null;

            } else {

                System.out.println("\n❌ Invalid Vaccination Status.");
                con.close();
                return;

            }

            String update = "UPDATE pet_medical_records "
                    + "SET vaccination_status = ?, "
                    + "last_vaccination_date = ?, "
                    + "next_vaccination_schedule = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(update);

            pst.setString(1, status);

            if (lastDate == null) {
                pst.setNull(2, Types.DATE);
            } else {
                pst.setDate(2, lastDate);
            }

            if (nextSchedule == null) {
                pst.setNull(3, Types.DATE);
            } else {
                pst.setDate(3, nextSchedule);
            }

            pst.setInt(4, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Vaccination status updated successfully!");

                if (lastDate != null) {
                    System.out.println("📅 Last Vaccination Date : " + lastDate);
                } else {
                    System.out.println("📅 Last Vaccination Date : NULL");
                }

                if (nextSchedule != null) {
                    System.out.println("📅 Next Vaccination Schedule : " + nextSchedule);
                } else {
                    System.out.println("📅 Next Vaccination Schedule : NULL");
                }

            } else {
                System.out.println("\n❌ Update failed.");
            }

            pst.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("\n❌ Error: " + e.getMessage());

        }
    }

    public static void updateLastVaccinationDate() {

        try {

            Connection con = DbConnection.getConnection();

            ShowPetList.showPetVetList(con);

            System.out.println("\n===== 📅 UPDATE LAST VACCINATION DATE =====");

            System.out.print("🆔 Enter Pet ID (press [0] to cancel): ");
            int petId = input.nextInt();

            input.nextLine();

            if (petId == 0) {
                System.out.println("↩ Returning to Veterinarian Menu...");
                return;
            }

            showVaccinations(con, petId);

            System.out.print("🆔 Vaccination ID: ");
            int vaccinationId = input.nextInt();
            input.nextLine();

            if (!exists(con, vaccinationId)) {
                System.out.println("\n❌ Invalid Vaccination ID.");
                return;
            }

            // Get current vaccination status
            String select = "SELECT vaccination_status FROM pet_medical_records WHERE vaccination_id = ?";

            PreparedStatement pstSelect = con.prepareStatement(select);
            pstSelect.setInt(1, vaccinationId);

            ResultSet rs = pstSelect.executeQuery();

            if (!rs.next()) {
                System.out.println("\n❌ Record not found.");
                rs.close();
                pstSelect.close();
                con.close();
                return;
            }

            String status = rs.getString("vaccination_status");

            rs.close();
            pstSelect.close();

            // Not Vaccinated cannot have a last vaccination date
            if (status.equalsIgnoreCase("Not Vaccinated")) {

                System.out.println("\n⚠ This pet is marked as 'Not Vaccinated'.");
                System.out.println("Change the vaccination status first before adding a vaccination date.");

                con.close();
                return;
            }

            System.out.print("📅 New Last Vaccination Date (yyyy-MM-dd): ");
            Date lastDate = Date.valueOf(input.nextLine());

            Date nextSchedule = null;

            // Auto-generate next schedule only for Partially Vaccinated
            if (status.equalsIgnoreCase("Partially Vaccinated")) {

                // Suggested next schedule (1 year)
                LocalDate suggestedDate = lastDate.toLocalDate().plusYears(1);
                nextSchedule = Date.valueOf(suggestedDate);

                System.out.println("📅 Suggested Next Vaccination Schedule: " + nextSchedule);

                System.out.print("🗓 Enter Next Vaccination Schedule (Press [ENTER] to use suggested): ");
                String customNext = input.nextLine();

                if (!customNext.trim().isEmpty()) {
                    nextSchedule = Date.valueOf(customNext);
                }

            } else if (status.equalsIgnoreCase("Fully Vaccinated")) {
                // Fully Vaccinated -> nextSchedule remains NULL

                System.out.println("\nℹ This pet is Fully Vaccinated.");
                System.out.println("The next vaccination schedule will remain NULL.");

                nextSchedule = null;
            }

            String update = "UPDATE pet_medical_records "
                    + "SET last_vaccination_date = ?, "
                    + "next_vaccination_schedule = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(update);

            pst.setDate(1, lastDate);

            if (nextSchedule == null) {
                pst.setNull(2, Types.DATE);
            } else {
                pst.setDate(2, nextSchedule);
            }

            pst.setInt(3, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {

                System.out.println("\n✅ Last vaccination date updated successfully!");

                if (status.equalsIgnoreCase("Partially Vaccinated")) {
                    System.out.println("📅 Next Vaccination Schedule: " + nextSchedule);
                } else {
                    System.out.println("📅 Next Vaccination Schedule remains: NULL");
                }

            } else {

                System.out.println("\n❌ Update failed.");

            }

            pst.close();
            con.close();

        } catch (IllegalArgumentException e) {

            System.out.println("\n❌ Invalid date format. Use yyyy-MM-dd.");

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    public static void updateNextVaccinationSchedule() {

        try {

            Connection con = DbConnection.getConnection();

            ShowPetList.showPetVetList(con);

            System.out.println("\n===== 🗓 UPDATE NEXT VACCINATION SCHEDULE =====");

            System.out.print("🆔 Enter Pet ID (press [0] to cancel): ");
            int petId = input.nextInt();

            input.nextLine();

            if (petId == 0) {
                System.out.println("↩ Returning to Veterinarian Menu...");
                con.close();
                return;
            }

            showVaccinations(con, petId);

            System.out.print("🆔 Vaccination ID: ");
            int vaccinationId = input.nextInt();
            input.nextLine();

            if (!exists(con, vaccinationId)) {
                System.out.println("\n❌ Invalid Vaccination ID.");
                con.close();
                return;
            }

            String query = "SELECT vaccination_status "
                    + "FROM pet_medical_records "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pstStatus = con.prepareStatement(query);

            pstStatus.setInt(1, vaccinationId);

            ResultSet rs = pstStatus.executeQuery();

            if (!rs.next()) {
                System.out.println("\n❌ Vaccination record not found.");
                rs.close();
                pstStatus.close();
                con.close();
                return;
            }

            String status = rs.getString("vaccination_status");

            rs.close();
            pstStatus.close();

            //==============================================
            // NOT VACCINATED CANNOT HAVE A NEXT SCHEDULE
            //==============================================
            if (status.equalsIgnoreCase("Not Vaccinated")) {

                System.out.println("\n⚠ This pet is marked as 'Not Vaccinated'.");
                System.out.println("Please update the vaccination status first.");

                con.close();
                return;
            }

            //=====================================================
            // FULLY VACCINATED SHOULD NOT HAVE A NEXT SCHEDULE
            //=====================================================
            if (status.equalsIgnoreCase("Fully Vaccinated")) {

                System.out.println("\n⚠ This pet is already 'Fully Vaccinated'.");
                System.out.println("The next vaccination schedule must remain NULL.");
                System.out.println("Change the vaccination status to 'Partially Vaccinated' first if another schedule is needed.");

                con.close();
                return;
            }

            //=========================================
            // ONLY PARTIALLY VACCINATED REACHES HERE
            //=========================================
            System.out.print("🗓 New Next Vaccination Schedule (yyyy-MM-dd)\n");
            Date nextSchedule = Date.valueOf(input.nextLine());

            String sql = "UPDATE pet_medical_records "
                    + "SET next_vaccination_schedule = ? "
                    + "WHERE vaccination_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setDate(1, nextSchedule);

            pst.setInt(2, vaccinationId);

            int rows = pst.executeUpdate();

            if (rows > 0) {

                System.out.println("\n✅ Next Vaccination Schedule updated successfully!");

                System.out.println("📅 Next Vaccination Schedule: " + nextSchedule);

            } else {

                System.out.println("\n❌ Update failed.");

            }

            pst.close();
            con.close();

        } catch (IllegalArgumentException e) {

            System.out.println("\n❌ Invalid date format. Please use yyyy-MM-dd.");

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
