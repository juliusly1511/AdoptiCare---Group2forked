package adminchoices.read;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SearchPet {

    public static void searchPet() {

        Scanner input = new Scanner(System.in);

        try {

            System.out.println("Enter Pet ID: ");
            int id = input.nextInt();
            input.nextLine();

            Connection con = DbConnection.getConnection();

            String sql = "SELECT * FROM pets WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            System.out.printf(
                    "%-8s %-15s %-12s %-5s %-8s %-15s %-25s%n",
                    "Pet ID",
                    "Pet Name",
                    "Species",
                    "Age",
                    "Gender",
                    "Breed",
                    "Description"
            );

            System.out.println("--------------------------------------------------------------------------------");

            if (rs.next()) {

                do {

                    System.out.printf(
                            "%-8d %-15s %-12s %-5d %-8s %-15s %-25s%n",
                            rs.getInt("pet_id"),
                            rs.getString("pet_name"),
                            rs.getString("species"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getString("breed"),
                            rs.getString("description")
                    );

                    System.out.println("--------------------------------------------------------------------------------");

                } while (rs.next());

            } else {
                System.out.println("Pet not found.");
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
