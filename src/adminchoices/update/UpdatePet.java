package adminchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import static util.ShowPetList.showPetList;

public class UpdatePet {

    static Scanner input = new Scanner(System.in);

    //=======================
    //PET MENU (UPDATE)
    //=======================
    public static void updatePetMenu() {

        int menu;

        do {
            System.out.println("\n✏ ===== UPDATE PET MENU =====");
            System.out.println("\n⚙ Which pet details would you like to update?");

            System.out.println("[1] 🐾 Update All Pet Details");
            System.out.println("[2] 🐶 Update Pet Name");
            System.out.println("[3] ⚧ Update Pet Gender");
            System.out.println("[4] 🎂 Update Pet Age");
            System.out.println("[5] 🐕 Update Pet Species");
            System.out.println("[6] 🏷 Update Pet Breed");
            System.out.println("[7] 📝 Update Pet Description");
            System.out.println("[8] ↩ Back");

            System.out.print("👉 Choose an option [1-8]: ");
            menu = input.nextInt();

            input.nextLine();

            switch (menu) {

                case 1:
                    updatePet();
                    break;

                case 2:
                    updatePetName();
                    break;

                case 3:
                    updatePetGender();
                    break;

                case 4:
                    updatePetAge();
                    break;

                case 5:
                    updatePetSpecies();
                    break;

                case 6:
                    updatePetBreed();
                    break;

                case 7:
                    updatePetDescription();
                    break;

                case 8:
                    System.out.println("\n↩ Going back...");
                    break;

                default:
                    System.out.println("\n❌ Invalid option.");
            }

        } while (menu != 8);
    }

    //=========================
    //UPDATE ALL PET DETAILS
    //=========================
    public static void updatePet() {
        // switch cases

        try {

            Connection con = DbConnection.getConnection();

            showPetList(con);
            System.out.println("\n===== 🐾 UPDATE PET DETAILS =====");

            System.out.print("🆔 Enter Pet ID (press [0] to cancel): ");
            int petId = input.nextInt();

            input.nextLine();
            
            if(petId == 0) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }

            System.out.print("🐶 New Pet Name: ");
            String petName = input.nextLine();

            System.out.print("⚧ New Gender: ");
            String gender = input.nextLine();

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

            System.out.println("Enter the pet's age in decimal format based on the legend above.\n"
                    + "Example: 0.50 = 6 Months, 1.25 = 1 Year 3 Months.");

            System.out.print("🎂 New Age [0-30]: ");
            double age;

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

            System.out.print("🐕 New Species: ");
            String species = input.nextLine();

            System.out.print("🏷 New Breed: ");
            String breed = input.nextLine();

            System.out.print("📝 New Description: ");
            String desc = input.nextLine();

            String queryPet
                    = "UPDATE pets "
                    + "SET pet_name = ?, "
                    + "gender = ?, "
                    + "age = ?, "
                    + "species = ?, "
                    + "breed = ?, "
                    + "description = ? "
                    + "WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(queryPet);

            pst.setString(1, petName);
            pst.setString(2, gender);
            pst.setDouble(3, age);
            pst.setString(4, species);
            pst.setString(5, breed);
            pst.setString(6, desc);
            pst.setInt(7, petId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet updated successfully.");
            } else {
                System.out.println("\n❌ Pet not found.");
            }

            con.close();

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    //===================
    //UPDATE PET NAME
    //===================
    public static void updatePetName() {

        try {
            Connection con = DbConnection.getConnection();

            showPetList(con);

            System.out.println("\n===== 🐶 UPDATE PET NAME =====");

            System.out.print("🆔 Enter Pet ID (press [0] to cancel): ");
            int petId = input.nextInt();

            input.nextLine();
            
            if(petId == 0) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }

            System.out.print("🐶 New Pet Name: ");
            String petName = input.nextLine();

            String queryName
                    = "UPDATE pets "
                    + "SET pet_name = ? "
                    + "WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(queryName);

            pst.setString(1, petName);
            pst.setInt(2, petId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet updated successfully.");
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    //=====================
    //UPDATE PET GENDER
    //=====================
    public static void updatePetGender() {

        try {
            Connection con = DbConnection.getConnection();

            showPetList(con);

            System.out.println("\n===== ⚧ UPDATE PET GENDER =====");

            System.out.print("🆔 Enter Pet ID (press [0] to cancel): ");
            int petId = input.nextInt();

            input.nextLine();
            
            if(petId == 0) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }

            System.out.print(" ⚧ New Gender: ");
            String gender = input.nextLine();

            String queryName
                    = "UPDATE pets "
                    + "SET gender = ? "
                    + "WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(queryName);

            pst.setString(1, gender);
            pst.setInt(2, petId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet updated successfully.");
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    //==================
    //UPDATE PET AGE
    //==================
    public static void updatePetAge() {

        try {
            Connection con = DbConnection.getConnection();

            showPetList(con);

            System.out.println("\n===== 🎂 UPDATE PET AGE =====");

            System.out.print("🆔 Enter Pet ID: ");
            int petId = input.nextInt();

            input.nextLine();

            if(petId == 0) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }
            
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

            System.out.println("Enter the pet's age in decimal format based on the legend above.\n"
                    + "Example: 0.50 = 6 Months, 1.25 = 1 Year 3 Months.");

            System.out.print("🎂 New Age [0-30]: ");
            double age;

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

            String queryName
                    = "UPDATE pets "
                    + "SET age = ? "
                    + "WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(queryName);

            pst.setDouble(1, age);
            pst.setInt(2, petId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet updated successfully.");
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    //====================
    //UPDATE PET SPECIES
    //====================
    public static void updatePetSpecies() {

        try {
            Connection con = DbConnection.getConnection();

            showPetList(con);

            System.out.println("\n===== 🐕 UPDATE PET SPECIES =====");

            System.out.print("🆔 Enter Pet ID: ");
            int petId = input.nextInt();

            input.nextLine();

            if(petId == 0) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }
            
            System.out.print("🐕 New Species: ");
            String species = input.nextLine();

            String queryName
                    = "UPDATE pets "
                    + "SET species = ? "
                    + "WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(queryName);

            pst.setString(1, species);
            pst.setInt(2, petId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet updated successfully.");
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    //===================
    //UPDATE PET BREED
    //===================
    public static void updatePetBreed() {

        try {
            Connection con = DbConnection.getConnection();

            showPetList(con);

            System.out.println("\n===== 🏷 UPDATE PET BREED =====");

            System.out.print("🆔 Enter Pet ID: ");
            int petId = input.nextInt();

            input.nextLine();

            if(petId == 0) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }
            
            System.out.print("🏷 New Breed: ");
            String breed = input.nextLine();

            String queryName
                    = "UPDATE pets "
                    + "SET breed = ? "
                    + "WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(queryName);

            pst.setString(1, breed);
            pst.setInt(2, petId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet updated successfully.");
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    //=========================
    //UPDATE PET DESCRIPTION
    //=========================
    public static void updatePetDescription() {

        try {
            Connection con = DbConnection.getConnection();

            showPetList(con);
            
            System.out.println("\n===== 📝 UPDATE PET DESCRIPTION =====");

            System.out.print("🆔 Enter Pet ID (press [0] to cancel): ");
            int petId = input.nextInt();

            input.nextLine();
            
            if(petId == 0) {
                System.out.println("↩ Returning to Admin Menu...");
                return;
            }
            

            System.out.print("📝 New Description: ");
            String desc = input.nextLine();

            String queryName
                    = "UPDATE pets "
                    + "SET description = ? "
                    + "WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(queryName);

            pst.setString(1, desc);
            pst.setInt(2, petId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet updated successfully.");
            } else {
                System.out.println("\n❌ Pet not found.");
            }

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
