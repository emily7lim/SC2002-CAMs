import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.Database;
import model.Camp;
import model.enums.Faculty;
import report.ReportController;
import report.ReportType;

public class App {
    public static void main(String[] args) {
        new Database();
        Database.USERS.forEach((userId, user) -> {
            System.out.println("UserID: " + userId + ", Name: " + user.getName());
        });

        writeReportExample();
    }

    private static void writeReportExample(){
        Camp exampleCamp = new Camp("CAMP1", "test camp", new Date(), new Date(1699012689503L), new Date(1699011699503L), Faculty.ADM, Faculty.ADM.getFaculty(), 10, 3, "test camp", "OURIN");
        List<Camp> camps = new ArrayList<Camp>();
        camps.add(exampleCamp);

        ReportController rp = new ReportController(ReportType.TXT);
        rp.generateAndWriteReports(camps);
    }
}
