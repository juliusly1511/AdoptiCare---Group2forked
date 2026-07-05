package adminchoices.create;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddPet {

    static Scanner input = new Scanner(System.in);

    public static void addPet() {

        try {
            System.out.print("Pet Name: ");
            String name = input.nextLine();

            System.out.print("Gender: ");
            String gender = input.nextLine();
            
            System.out.print("Age: ");
            int age = input.nextInt();
            input.nextLine();

            System.out.print("Species: ");
            String species = input.nextLine();

            System.out.print("Breed: ");
            String breed = input.nextLine();

            System.out.print("Description: ");
            String description = input.nextLine();
            
            Connection con = DbConnection.getConnection();

            String sql
                    = "INSERT INTO pets "
                    + "(pet_name, gender, age, species, breed, description, adoption_status, date_added) "
                    + "VALUES (?, ?, ?, ?, ?, ?, 'Available', CURRENT_TIMESTAMP);";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, name);
            pst.setString(2, gender);
            pst.setInt(3, age);
            pst.setString(4, species);
            pst.setString(5, breed);
            pst.setString(6, description);
            
            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\nPet Added Successfully!");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
