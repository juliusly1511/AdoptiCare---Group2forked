package adminchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import util.AgeConverter;

public class SearchPet {

    //================
    //SEARCH PET MENU
    //================
    public static void searchPetMenu() {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== 🔍 SEARCH PET =====");
            System.out.println("\n⚙ Which would you like to search?");
            System.out.println("[1] 🐾 Search by Species");
            System.out.println("[2] 🏷 Search by Breed");
            System.out.println("[3] 🎂 Search by Age");
            System.out.println("[4] ↩ Back");
            System.out.print("\n👉 Choose an option [1-4]: ");

            if (!input.hasNextInt()) {
                System.out.println("❌ Invalid input!");
                input.nextLine();
                continue;
            }

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    searchBySpecies();
                    break;

                case 2:
                    searchByBreed();
                    break;

                case 3:
                    searchByAge();
                    break;

                case 4:
                    System.out.println("\n↩ Going back...");
                    return;

                default:
                    System.out.println("\n❌ Invalid option.");
            }
        }
    }

    //==================
    //SEARCH BY SPECIES
    //==================
    public static void searchBySpecies() {

        try {

            System.out.println("\n===== 🔍 SEARCH PET BY SPECIES 🐾 =====");

            Scanner input = new Scanner(System.in);

            System.out.print("🐾 Species (type [Cancel] to cancel): ");
            String species = input.nextLine();

            if (species.equalsIgnoreCase("Cancel")) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }

            Connection con = DbConnection.getConnection();

            String sql
                    = "SELECT * FROM pets WHERE species LIKE ? "
                    + "AND archived = 0";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "%" + species + "%");

            ResultSet rs = pst.executeQuery();

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

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

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

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

                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                } while (rs.next());

            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    //=================
    //SEARCH BY BREED
    //=================
    public static void searchByBreed() {

        try {

            System.out.println("\n===== 🔍 SEARCH PET BY BREED 🏷 =====");

            Scanner input = new Scanner(System.in);

            System.out.print("🏷 Breed (type [Cancel] to cancel): ");
            String breed = input.nextLine();

            if (breed.equalsIgnoreCase("Cancel")) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }

            Connection con = DbConnection.getConnection();

            String sql
                    = "SELECT * FROM pets WHERE breed LIKE ? "
                    + "AND archived = 0";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "%" + breed + "%");

            ResultSet rs = pst.executeQuery();

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

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

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

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

                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                } while (rs.next());

            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    //===============
    //SEARCH BY AGE
    //===============
    public static void searchByAge() {

        try {

            System.out.println("\n===== 🔍 SEARCH PET BY AGE 🎂 =====");

            Scanner input = new Scanner(System.in);

            System.out.println("\n========== AGE LEGEND ==========");
            System.out.println("0.08 = 1 Month");
            System.out.println("0.17 = 2 Months");
            System.out.println("0.25 = 3 Months");
            System.out.println("0.33 = 4 Months");
            System.out.println("0.42 = 5 Months");
            System.out.println("0.50 = 6 Months");
            System.out.println("0.58 = 7 Months");
            System.out.println("0.67 = 8 Months");
            System.out.println("0.75 = 9 Months");
            System.out.println("0.83 = 10 Months");
            System.out.println("0.92 = 11 Months");
            System.out.println("1.00 = 1 Year");
            System.out.println("1.25 = 1 Year 3 Months");
            System.out.println("1.50 = 1 Year 6 Months");
            System.out.println("===============================\n");

            System.out.println("Search pets by age range.");
            System.out.println("Example:");
            System.out.println("Minimum Age = 0");
            System.out.println("Maximum Age = 10");
            System.out.println("Displays pets from 0 to 10 years old.");

            System.out.println("Enter the pet's age in decimal format based on the legend above.\n"
                    + "Example: 0.50 = 6 Months, 1.25 = 1 Year 3 Months.");

            //=========================
            // MINIMUM AGE
            //=========================
            double minAge;

            while (true) {

                System.out.print("\n🎂 Minimum Age [0-25] (press [-1] to cancel): ");

                if (!input.hasNextDouble()) {
                    System.out.println("\n⚠ Invalid input: Input must be numerical.\n");
                    input.nextLine();
                    continue;
                }

                minAge = input.nextDouble();
                input.nextLine();

                if (minAge == -1) {
                    System.out.println("\n↩ Returning to Search Menu...");
                    return;
                }

                if (minAge < 0 || minAge > 25) {
                    System.out.println("\n⚠ Age must be between 0 and 25.\n");
                    continue;
                }

                break;
            }

            //=========================
            // MAXIMUM AGE
            //=========================
            double maxAge;

            while (true) {

                System.out.print("🎂 Maximum Age [0-25]: ");

                if (!input.hasNextDouble()) {
                    System.out.println("\n⚠ Invalid input: Input must be numerical.\n");
                    input.nextLine();
                    continue;
                }

                maxAge = input.nextDouble();
                input.nextLine();

                if (maxAge < minAge || maxAge > 25) {
                    System.out.println("\n⚠ Maximum age must be greater than or equal to the minimum age.\n");
                    continue;
                }

                break;
            }

            Connection con = DbConnection.getConnection();

            String sqlAge
                    = "SELECT * FROM pets "
                    + "WHERE age BETWEEN ? AND ? "
                    + "AND archived = 0 "
                    + "ORDER BY age";

            PreparedStatement pst = con.prepareStatement(sqlAge);

            pst.setDouble(1, minAge);
            pst.setDouble(2, maxAge);

            ResultSet rs = pst.executeQuery();

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

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

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

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

                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                } while (rs.next());

            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
