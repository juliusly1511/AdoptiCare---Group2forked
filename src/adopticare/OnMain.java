package adopticare;

import database.AdminVetSeeder;

public class OnMain {

    public static void main(String[] args) {

        // Ensure admin & veterinarian accounts exist (prompt if missing)
        AdminVetSeeder.ensureAdminVetAccountsExist();

        //STARTING POINT
        AdoptiCareMain.onSystem();
    }
}

