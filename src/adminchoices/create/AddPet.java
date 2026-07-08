package adminchoices.create;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import util.AgeConverter;

public class AddPet {

    static Scanner input = new Scanner(System.in);

    public static void addPet() {

        try {
            System.out.println("\n===== ➕ ADD NEW PET =====");

            String name, gender;
            double age;

            while (true) {

                //============
                //PET NAME
                //============
                System.out.print("🐶 Pet Name: ");
                name = input.nextLine().trim();

                if (name.isEmpty()) {
                    System.out.println("\n⚠ Pet name cannot be empty!\n");
                    continue;
                }

                //============
                //GENDER
                //============
                System.out.print("⚧ Gender (Male/Female): ");
                gender = input.nextLine().trim();

                if (gender.isEmpty()) {
                    System.out.println("\n⚠ Gender cannot be empty!\n");
                    continue;
                }

                //===========
                //AGE
                //===========
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

                System.out.print("🎂 Age [0-30]: ");

                if (!input.hasNextDouble()) {
                    System.out.println("\n⚠ Invalid age input!\n");
                    input.nextLine();
                    continue;
                }

                age = input.nextDouble();
                input.nextLine();

                if (age < 0 || age > 30) {
                    System.out.println("\n⚠ Invalid age range: Age must be between 0 to 30.\n");
                    continue;
                }

                System.out.println(AgeConverter.convertAge(age));
                break;

            }

            //=============
            //SPECIES
            //=============
            System.out.print("🐕 Species: ");
            String species = input.nextLine().trim();

            //============
            //BREED
            //============
            System.out.print("🏷 Breed: ");
            String breed = input.nextLine().trim();

            //============
            //DESCRIPTION
            //============
            System.out.print("📝 Description: ");
            String description = input.nextLine().trim();

            Connection con = DbConnection.getConnection();

            //=============
            //INSERT QUERY
            //=============
            String sql
                    = "INSERT INTO pets "
                    + "(pet_name, gender, age, species, breed, description, adoption_status, date_added) "
                    + "VALUES (?, ?, ?, ?, ?, ?, 'Available', CURRENT_TIMESTAMP);";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, gender);
            pst.setDouble(3, age);
            pst.setString(4, species);
            pst.setString(5, breed);
            pst.setString(6, description);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet Added Successfully!");
            } else {
                System.out.println("\n❌ Failed to Add Pet.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
