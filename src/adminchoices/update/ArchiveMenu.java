package adminchoices.update;

import adminchoices.read.SearchArchivedPet;
import adminchoices.read.ViewArchivedPets;
import adminchoices.read.ViewRequests;
import java.util.Scanner;
import java.sql.*;
import database.DbConnection;
import menu.Admin;
import static util.ShowPetList.showPetList;

public class ArchiveMenu {

    static Scanner input = new Scanner(System.in);

    //=====================
    //ARCHIVED PET MENU
    //=====================
    public static void archivedMenu() {

        while (true) {
            System.out.println("\n🗄 ===== ARCHIVED MENU =====");
            System.out.println("[1] 🗃 Archive Pet");
            System.out.println("[2] 📋 View Archived Pets");
            System.out.println("[3] 📋 View Archived Requests");
            System.out.println("[4] ♻ Restore Pet");
            System.out.println("[5] 🔍 Search Archived Pet");
            System.out.println("[6] ↩ Return to Admin Menu");

            System.out.print("\n👉 Choose an Option [1-5]: ");

            int choice;

            if (!input.hasNextInt()) {
                System.out.println("❌ Invalid input!");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();

            input.nextLine();

            switch (choice) {

                case 1:
                    archivePet();
                    break;

                case 2:
                    ViewArchivedPets.viewArchivedPets();
                    break;
                    
                case 3:
                    ViewRequests.viewArchivedRequests();
                    break;
                    
                case 4:
                    RestoreArchivedPets.restorePet();
                    break;

                case 5:
                    SearchArchivedPet.searchArchivedPet();
                    break;

                case 6:
                    Admin.adminMenu();
                    System.out.println("\n↩ Returning to Admin Menu...");
                    break;

                default:
                    System.out.println("❌ Invalid option!");
            }
        }
    }

    //===================
    //ARCHIVED PETS
    //===================
    public static void archivePet() {

        try {

            Connection con = DbConnection.getConnection();
            
            showPetList(con);
            
            System.out.print("\n🆔 Enter Pet ID (press 0 to cancel): ");
            int petId;

            while (true) {
                if (!input.hasNextInt()) {
                    System.out.println("\n⚠ Invalid input: Please enter a number.\n");
                    input.nextLine();
                    continue;
                }

                petId = input.nextInt();
                
                input.nextLine();

                if (petId == 0) {
                    System.out.println("\n↩ Returning to Archive Menu...");
                    return;
                }
                
                break;
            }

            String sql
                    = "UPDATE pets "
                    + "SET archived = 1 "
                    + "WHERE pet_id = ?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, petId);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("\n✅ Pet archived successfully.");
            } else {
                System.out.println("\n❌ Archive failed.");
            }

            con.close();

        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
