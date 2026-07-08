package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowPetList {

    public static void showPetList(Connection con) throws SQLException {

        try {
            String sql = "SELECT pet_id, pet_name, species, breed, adoption_status "
                    + "FROM pets "
                    + "WHERE archived = 0 "
                    + "ORDER BY pet_id";

            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();

            System.out.println("\n==================== PET LIST ====================");

            System.out.printf(
                    "| %-8s | %-20s | %-12s | %-18s | %-15s |%n",
                    "Pet ID",
                    "Pet Name",
                    "Species",
                    "Breed",
                    "Status"
            );

            System.out.println("-----------------------------------------------------------------------------------");

            if(!rs.next()) {
                System.out.println("|❌ No pets found. |");
            }
            
            do {

                System.out.printf(
                        "| %-8d | %-20s | %-12s | %-18s | %-15s |%n",
                        rs.getInt("pet_id"),
                        rs.getString("pet_name"),
                        rs.getString("species"),
                        rs.getString("breed"),
                        rs.getString("adoption_status")
                );
            } while (rs.next());

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
    
    public static void showArchivePetList(Connection con) throws SQLException {

        try {
            String sql = "SELECT pet_id, pet_name, species, breed, adoption_status "
                    + "FROM pets "
                    + "WHERE archived = 1 "
                    + "ORDER BY pet_id";

            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();

            System.out.println("\n==================== PET LIST ====================");

            System.out.printf(
                    "| %-8s | %-20s | %-12s | %-18s | %-15s |%n",
                    "Pet ID",
                    "Pet Name",
                    "Species",
                    "Breed",
                    "Status"
            );

            System.out.println("-----------------------------------------------------------------------------------");

            if(!rs.next()) {
                System.out.println("|❌ No pets found. |");
            }
            
            do {

                System.out.printf(
                        "| %-8d | %-20s | %-12s | %-18s | %-15s |%n",
                        rs.getInt("pet_id"),
                        rs.getString("pet_name"),
                        rs.getString("species"),
                        rs.getString("breed"),
                        rs.getString("adoption_status")
                );
            } while (rs.next());

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
    
    public static void showPetVetList(Connection con) throws SQLException {

        try {
            String sql = "SELECT pet_id, pet_name, species, breed, adoption_status "
                    + "FROM pets "
                    + "ORDER BY pet_id";

            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();

            System.out.println("\n==================== PET LIST ====================");

            System.out.printf(
                    "| %-8s | %-20s | %-12s | %-18s | %-15s |%n",
                    "Pet ID",
                    "Pet Name",
                    "Species",
                    "Breed",
                    "Status"
            );

            System.out.println("-----------------------------------------------------------------------------------");

            if(!rs.next()) {
                System.out.println("|❌ No pets found. |");
            }
            
            do {

                System.out.printf(
                        "| %-8d | %-20s | %-12s | %-18s | %-15s |%n",
                        rs.getInt("pet_id"),
                        rs.getString("pet_name"),
                        rs.getString("species"),
                        rs.getString("breed"),
                        rs.getString("adoption_status")
                );
            } while (rs.next());

            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}
