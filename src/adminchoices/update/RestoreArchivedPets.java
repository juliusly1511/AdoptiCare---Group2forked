package adminchoices.update;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RestoreArchivedPets {

    public static void restorePet() {

        Scanner input = new Scanner(System.in);

        try {
            System.out.println("\n===== ♻ RESTORE ARCHIVED PET =====");
            
            Connection con = DbConnection.getConnection();

            int petId;

            System.out.print("🆔 Enter Pet ID to restore: ");

            while (true) {
                if (!input.hasNextInt()) {
                    System.out.println("❌ Invalid input.");
                    input.nextLine();
                    continue;
                }

                petId = input.nextInt();
                input.nextLine();

                break;
            }
            String checkSql
                    = "SELECT pet_name "
                    + "FROM pets "
                    + "WHERE pet_id = ? AND archived = 1";

            PreparedStatement pst = con.prepareStatement(checkSql);

            pst.setInt(1, petId);

            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Archived pet not found.");
                System.out.println("\n👉 Press [Enter] to return to the Admin Menu...");
                input.nextLine();
                con.close();
                return;
            }

            String name = rs.getString("pet_name");
            String confirm;
            
            while(true) {

                System.out.print("⚠ Are you sure to restore " + name + "? (Y/N): ");
                confirm = input.nextLine().trim();
                
                if (confirm.equalsIgnoreCase("Y")) {
                    break;
                }
                
                if (confirm.equalsIgnoreCase("N")) {
                    System.out.println("\n↩ Restore cancelled.");
                    ArchivePetMenu.archivedPetsMenu();
                    return;
                }
                
                System.out.println("\n⚠ Invalid choice: Please enter Y or N.");
            }
            
            String Updatesql =
                    "UPDATE pets "
                    + "SET archived = 0 "
                    + "WHERE pet_id = ?";
            
            PreparedStatement pstUpdate = con.prepareStatement(Updatesql);
            
            pstUpdate.setInt(1, petId);
            
            int row = pstUpdate.executeUpdate();
            
            if (row > 0) {
                System.out.println("\n✅ Pet restored successfully!");
            }
            
            con.close();
            
        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
