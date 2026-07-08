package adminchoices.delete;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import menu.Admin;
import util.ShowPetList;

public class DeletePet {

    public static void deletePet() {

        Scanner input = new Scanner(System.in);

        try {

            Connection con = DbConnection.getConnection();
            
            System.out.println("\n===== 🗑 DELETE PET =====");

            int petId;

            //==============================
            //INPUT VALIDATION PET ID
            //==============================
            while (true) {

                ShowPetList.showPetList(con);
                
                System.out.print("🆔 Enter Pet ID (press 0 to cancel): ");

                if (!input.hasNextInt()) {
                    System.out.println("\n⚠ Invalid input: Pet ID must be a number.\n");
                    input.nextLine();
                    continue;
                }

                petId = input.nextInt();

                input.nextLine();

                if (petId <= 0) {
                    System.out.println("\n⚠ Pet ID must be greater than 0!\n");
                    continue;
                }
                
                if (petId == 0) {
                    System.out.println("↩ Returning to Admin Menu...\n");
                    return;
                }

                break;
            }

            String checkSql
                    = "SELECT pet_id, pet_name "
                    + "FROM pets "
                    + "WHERE pet_id = ?";

            PreparedStatement checkPst = con.prepareStatement(checkSql);

            checkPst.setInt(1, petId);

            ResultSet rs = checkPst.executeQuery();

            //=============================
            //!TRUE(FALSE) IF THERE'S NO NEXT ROW
            //=============================
            if (!rs.next()) {
                System.out.println("\n❌ Pet not found.");
                return;
            }

            String petName = rs.getString("pet_name");

            String confirm;

            while (true) {
                //===================
                //CONFIRMATION
                //===================
                System.out.println("\n⚠ You are about to delete: " + petName);
                System.out.print("Are you sure? (Y/N): ");
                confirm = input.nextLine().trim();

                if (confirm.equalsIgnoreCase("Y")) {
                    break;
                }
                
                if (confirm.equalsIgnoreCase("N")) {
                    System.out.println("\n↩ Deletion cancelled.");
                    Admin.adminMenu(); // Go back to Admin Menu
                    return;
                }
                
                System.out.println("\n❌ Invalid choice: Please enter Y or N.");
            }
            
            //==================
            //DELETE QUERY
            //==================
            String sql
                    = "DELETE "
                    + "FROM pets "
                    + "WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, petId);

            int rows = pst.executeUpdate();
            
            //===================
            //RESULT
            //===================
            if (rows > 0) {
                System.out.println("\n✅ Pet deleted successfully!");
            } else {
                System.out.println("\n❌ Pet not found.");
            }

            con.close();

        } catch (SQLException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
