package customerchoices;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import util.AgeConverter;

public class SearchPetMenu {

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
                    System.out.println("/n↩ Going back...");
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

            System.out.print("\n🐾 Species (type [Cancel] to cancel): ");
            String species = input.nextLine();

            if (species.equalsIgnoreCase("Cancel")) {
                System.out.println("↩ Returning to Customer Menu...");
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

                System.out.print("\n🆔 Enter Pet ID to see more details (press 0 to cancel): ");

                if (!input.hasNextInt()) {
                    System.out.println("\n❌ Invalid input. Returning to menu...");
                    return;
                }

                int petId = input.nextInt();

                if (petId == 0) {
                    System.out.println("\n↩ Returning to menu...");
                    return;
                }

                ViewPetDetails.viewPetDetails(petId);
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

            System.out.print("\n🏷 Breed: ");
            String breed = input.nextLine();

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

                System.out.print("\n🆔 Enter Pet ID to see more details (press 0 to cancel): ");

                if (!input.hasNextInt()) {
                    System.out.println("❌ Invalid input. Returning to menu...");
                    return;
                }

                int petId = input.nextInt();

                if (petId == 0) {
                    System.out.println("↩ Returning to menu...");
                    return;
                }

                ViewPetDetails.viewPetDetails(petId);
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

            double age;

            System.out.print("🎂 Age [0-30]: ");

            while (true) {

                if (!input.hasNextDouble()) {
                    System.out.println("\n⚠ Invalid input: Input must be numerical.\n");
                    continue;
                }

                age = input.nextDouble();
                input.nextLine();

                if (age < 0 || age > 30) {
                    System.out.println("\n⚠ Invalid input: Age must be between 0 to 30.\n");
                    continue;
                }

                break;
            }

            Connection con = DbConnection.getConnection();

            String sqlAge
                    = "SELECT * FROM pets WHERE age LIKE ? "
                    + "AND archived = 0";

            PreparedStatement pst = con.prepareStatement(sqlAge);

            pst.setString(1, "%" + age + "%");

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

                System.out.print("\n🆔 Enter Pet ID to see more details (press 0 to cancel): ");

                if (!input.hasNextInt()) {
                    System.out.println("\n❌ Invalid input. Returning to menu...");
                    return;
                }

                int petId = input.nextInt();

                if (petId == 0) {
                    System.out.println("\n↩ Returning to menu...");
                    return;
                }

                ViewPetDetails.viewPetDetails(petId);
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
