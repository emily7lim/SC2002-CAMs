package controller;

import model.enums.Faculty;
import model.enums.Role;

public class StudentManager {
    public static final Role role = Role.STUDENT;

    public static void initializeUserData() {
        String password = "password";
        UserManager.createUser("YCHERN", "CHERN", password, Faculty.SCSE, role);
        UserManager.createUser("KOH1", "KOH", password, Faculty.ADM, role);
        UserManager.createUser("BR015", "BRANDON", password, Faculty.EEE, role);
        UserManager.createUser("CT113", "CALVIN", password, Faculty.SCSE, role);
        UserManager.createUser("YCN019", "CHAN", password, Faculty.NBS, role);
        UserManager.createUser("DL007", "DENISE", password, Faculty.SCSE, role);
        UserManager.createUser("DON84", "DONG", password, Faculty.ADM, role);
        UserManager.createUser("ELI34", "ERNEST", password, Faculty.EEE, role);
        UserManager.createUser("LE51", "LEE", password, Faculty.SCSE, role);
        UserManager.createUser("SL22", "LIU", password, Faculty.NBS, role);
        UserManager.createUser("AKY013", "RAWAL", password, Faculty.SSS, role);
    }
}
