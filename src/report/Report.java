package report;

import java.util.ArrayList;
import java.util.List;

import model.Camp;
import model.Student;

public class Report {
    private StringBuilder reportContent;
    
    public Report(){
        this.reportContent = new StringBuilder();
    }

    public String getReportContent() {
        return reportContent.toString();
    }
    public void clearReportContent() {
        this.reportContent.setLength(0);
    }

    public String generateCampListReport(List<Camp> camps, ReportType reportType) {
        // Generate the report content based on the selected reportFormat
        switch (reportType) {
            case TXT:
                reportContent.append("Camp List Report (Text):\n");
                for (Camp camp : camps) {
                    reportContent.append("Camp Id: " + camp.getCampId() + "\n");
                    reportContent.append("Camp Name: " + camp.getName() + "\n");
                    reportContent.append("Start Date: " + camp.getStartDate() + "\n");
                    reportContent.append("Location: " + camp.getLocation() + "\n");
                    reportContent.append("\n");
                }
                break;
            case CSV:
                reportContent.append("Camp Name,Start Date,Location\n");
                for (Camp camp : camps) {
                    reportContent.append(camp.getName() + "," + 
                                         camp.getStartDate() + "," + 
                                         camp.getLocation() + "\n");
                }
                break;
            default:
                System.out.println("did not implement the formatting for: " + reportType);
                break;
        }
        return reportContent.toString();
    }

    //TODO
    public String generateCampCommitteePerformanceReport(List<Camp> camps, ReportType reportType) {
        System.out.println("Report.generateCampCommitteePerformanceReport() is unimplemented");
        return "";
    }

    // Helper method to retrieve camp committee members for a camp
    private List<Student> getCommitteeMembers(List<Student> students, int campId) {
        List<Student> committeeMembers = new ArrayList<>();
        return committeeMembers;
    }
}
