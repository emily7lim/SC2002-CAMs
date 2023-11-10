package report;

import java.util.ArrayList;
import java.util.List;

import controller.StudentController;
import model.Camp;
import model.Student;
import report.enums.ReportOutputType;

/**
 * Generates report content for different report types, including camp lists and committee performance reports.
 * 
 * @author Choh Lit Han Owen
 * @version 1.1
 * @since 2023-11-03
 */
public class Report {
    //TODO
    // make this class static?
    private StringBuilder reportContent;
    
    public Report(){
        this.reportContent = new StringBuilder();
    }

    /**
     * Get the generated report content.
     *
     * @return The generated report content.
     */
    public String getReportContent() {
        return reportContent.toString();
    }

    /**
     * Clear the report content.
     */
    public void clearReportContent() {
        this.reportContent.setLength(0);
    }

    /**
     * Generate a camp list report based on the specified report output type.
     *
     * @param camps The list of camps to include in the report.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @return The generated camp list report.
     */
    public String generateCampListReport(List<Camp> camps, ReportOutputType reportOutputType) {
        // Generate the report content based on the selected reportFormat
        switch (reportOutputType) {
            case TXT:
                campListTxtFormatter(camps);
                break;
            case CSV:
                campListCsvFormatter(camps);
                break;
            default:
                System.out.println("did not implement the formatting for: " + reportOutputType);
                break;
        }
        return reportContent.toString();
    }

    /**
     * Helper function for generateCampListReport
     * Format the camp list report for TXT output.
     *
     * @param camps The list of camps to include in the report.
     */
    private void campListTxtFormatter(List<Camp> camps) {
        reportContent.append("Camp List Report:\n");
        for (Camp camp : camps) {
            reportContent.append("Camp Id: " + camp.getCampId() + "\n");
            reportContent.append("Camp Name: " + camp.getName() + "\n");
            reportContent.append("Start Date: " + camp.getStartDate() + "\n");
            reportContent.append("Location: " + camp.getLocation() + "\n");
            reportContent.append("\n");
        }
    }

    /**
     * Helper function for generateCampListReport
     * Format the camp list report for CSV output.
     *
     * @param camps The list of camps to include in the report.
     */
    private void campListCsvFormatter(List<Camp> camps) {
        this.reportContent.append("Camp Name,Start Date,Location\n");
        for (Camp camp : camps) {
            // Properly format fields with quotes for CSV
            this.reportContent.append(
                formatCsvField(camp.getName()) + "," +
                formatCsvField(camp.getStartDate().toString()) + "," +
                formatCsvField(camp.getLocation()) + "\n"
            );
        }
    }

    /**
     * Format a field for CSV output, for dealing with special characters.
     *
     * @param field The field to format.
     * @return The properly formatted field.
     */
    private String formatCsvField(String field) {
        // If the field contains a special character or whitespace, enclose it within double quotes
        // Plus if the field contains double quotes, escape them
        if (field.contains(",") || field.contains("\n") || field.contains("\r") || field.contains("\t") || field.contains("\"")) {
            field = field.replace("\"", "\"\"");
            String ans = "\"" + field + "\"";
            System.out.println(ans);
            return ans;
        } else {
            System.out.println(field);
            return field;
        }
    }

    /**
     * Generate a committee performance report based on the specified report output type.
     *
     * @param camps The list of camps to include in the report.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @return The generated committee performance report.
     */
    public String generateCampCommitteePerformanceReport(List<Camp> camps, ReportOutputType reportOutputType) {
        System.out.println("in generateCampCommitteePerformanceReport() function");
        switch (reportOutputType) {
            case TXT:
                commPerfTxtFormatter(camps);
                break;
            case CSV:
                commPerfCsvFormatter(camps);
                break;
            default:
                System.out.println("did not implement the formatting for: " + reportOutputType);
                break;
        }
        return reportContent.toString();
    }

    /**
     * Helper function for generateCampCommitteePerformanceReport
     * Format the committee performance report for TXT output.
     *
     * @param camps The list of camps to include in the report.
     */
    private void commPerfTxtFormatter(List<Camp> camps){
        reportContent.append("Camp Committee Performance Report:\n");
        for (Camp camp : camps) {
            List<Student> committeeMembers = getCommitteeMembers(camp);
            reportContent.append("Camp Id: " + camp.getCampId() + "\n");
            for(Student member : committeeMembers){
                reportContent.append("Committee Member Name: " + member.getName() + "\n");
                reportContent.append("Committee Points: " + member.getPoints() + "\n");
            }
            reportContent.append("\n");
        }
    }

    /**
     * Helper function for generateCampCommitteePerformanceReport
     * Format the committee performance report for CSV output.
     *
     * @param camps The list of camps to include in the report.
     */
    private void commPerfCsvFormatter(List<Camp> camps){
        this.reportContent.append("Camp Id,Committee Member Name,Committee Points\n");

        for (Camp camp : camps) {
            // Properly format fields with quotes for CSV
            String campName = formatCsvField(camp.getName());
            List<Student> committeeMembers = getCommitteeMembers(camp);

            for(Student member : committeeMembers){
                this.reportContent.append(
                    campName + "," +
                    formatCsvField(member.getName()) + "," +
                    member.getPoints() + "\n"
                );
            }
            
        }
    }

    /**
     * Helper method to retrieve camp committee members for a camp
     *
     * @param camp The camp for which to retrieve committee members.
     * @return A list of committee members.
     */
    private List<Student> getCommitteeMembers(Camp camp) {
        ArrayList<String> commIds = camp.getCommitteeIds();

        StudentController ctrl = new StudentController(); // might be static?
        
        List<Student> committeeMembers = new ArrayList<>();

        for (String id : commIds) {
            committeeMembers.add(ctrl.getStudentByUserId(id));
        }
        return committeeMembers;
    }
}
