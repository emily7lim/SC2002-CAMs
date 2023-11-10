import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.Database;
import model.Camp;
import model.enums.Faculty;
import report.ReportController;
import report.enums.ReportType;
import report.enums.ReportOutputType;

public class App {
    public static void main(String[] args) {
        new Database();
        Database.USERS.forEach((userId, user) -> {
            System.out.println("UserID: " + userId + ", Name: " + user.getName());
        });

        writeReportExample();
    }

    private static void writeReportExample(){
        List<Camp> camps = new ArrayList<Camp>();
        Camp exampleCamp1 = new Camp("test' \"camp", new Date(), new Date(1699012689503L), new Date(1699011699503L), Faculty.ADM, Faculty.ADM.getFaculty(), 10, 3, "test camp", "OURIN");
        camps.add(exampleCamp1);
        Camp exampleCamp2 = new Camp("test camp2", new Date(), new Date(1699012689503L), new Date(1699011699503L), Faculty.ADM, Faculty.ADM.getFaculty(), 15, 3, "test camp2", "OURIN");
        camps.add(exampleCamp2);
        
        // test string cos formatting might mess up 
        String testCsv = "\"Field with a comma, inside quotes\",\"Field with \"double\" quotes\"," +
                "\"Field with line break or newline\",\"Field with tab\tspace\",\"1234560001\"";
        Camp exampleCamp3 = new Camp(testCsv, new Date(), new Date(1699012689503L), new Date(1699011699503L), Faculty.ADM, Faculty.ADM.getFaculty(), 15, 3, "to test formatting", "OURIN");
        camps.add(exampleCamp3);

        ReportController rp = new ReportController(ReportOutputType.CSV);
        rp.generateAndWriteReports(camps, ReportType.CAMP_LIST);
    }
}
