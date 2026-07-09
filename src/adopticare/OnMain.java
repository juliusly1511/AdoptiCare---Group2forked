package adopticare;

import database.AdminVetSeeder;

public class OnMain {

    public static void main(String[] args) {

        // Seed default admin & veterinarian accounts (only if they don't exist)
        AdminVetSeeder.seedDefaultAccounts();

        //STARTING POINT
        AdoptiCareMain.onSystem();
    }
}

