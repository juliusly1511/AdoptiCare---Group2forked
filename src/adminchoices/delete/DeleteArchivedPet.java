package adminchoices.delete;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import static util.ShowPetList.showPetList;

public class DeleteArchivedPet {
    
    public static void deleteArchivedPet() {
        Scanner input = new Scanner(System.in);
        
        try {
            Connection con = DbConnection.getConnection();
            
            while (true) {
                showPetList(con);
                
                System.out.println("\n===== 🗑 DELETE ARCHIVED PET =====");
                
                System.out.print("🆔 Enter Archived Pet ID (press 0 to cancel): ");
                
                if (!input.hasNextInt()) {
                    System.out.println("\n⚠ Invalid input: Please enter a number.\n");
                    input.nextLine();
                    continue;
                }
                
                int petId = input.nextInt();
                input.nextLine();
                
                if (petId == 0) {
                    System.out.println("\n↩ Returning to Archived Pets Menu...\n");
                    return;
                }
                
                String checkSql = "SELECT pet_name "
                        + "FROM pets "
                        + "WHERE pet_id = ? AND archived = 1";
                
                PreparedStatement checkSt = con.prepareStatement(checkSql);
                
                checkSt.setInt(1, petId);
                
                ResultSet rs = checkSt.executeQuery();
                
                if (!rs.next()) {
                    System.out.println("❌ Archived pet not found.");
                    continue;
                }
                
                String petName = rs.getString("pet_name");
                
                while (true) {
                    System.out.println("⚠ Permanently delete \"" + petName + "\"? (Y/N): ");
                    String confirm = input.nextLine().trim();
                    
                    if (confirm.equalsIgnoreCase("Y")) {
                        break;
                    }
                    
                    if (confirm.equalsIgnoreCase("N")) {
                        System.out.println("\n↩ Deletion cancelled.");
                        return;
                    }
                    
                    System.out.println("\n⚠ Please enter Y or N.");
                }
                
                String deleteSql = "DELETE "
                        + "FROM pets "
                        + "WHERE pet_id = ? AND archived = 1";
                
                PreparedStatement deleteSt = con.prepareStatement(deleteSql);
                
                deleteSt.setInt(1, petId);
                
                int rows = deleteSt.executeUpdate();
                
                if (rows > 0) {
                    System.out.println("\n✅ Archived pet permanently deleted.");
                } else {
                    System.out.println("\n❌ Failed to delete archived pet.");
                }
                
                break;
            }
            
            con.close();
            
        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
