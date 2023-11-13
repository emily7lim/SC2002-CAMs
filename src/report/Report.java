package report;

import java.util.ArrayList;
import java.util.List;

import controller.EnquiryController;
import controller.StudentController;
import controller.SuggestionController;
import model.Camp;
import model.Enquiry;
import model.Student;
import model.Suggestion;
import report.enums.ReportOutputType;

/**
 * Generates report content for different report types, including camp lists and
 * committee performance reports.
 * 
 * @author Choh Lit Han Owen
 * @version 1.1
 * @since 2023-11-03
 */
public class Report {
    // TODO
    // make this class static?
    private StringBuilder reportContent;

    public Report() {
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
     * @param camps            The list of camps to include in the report.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @return The generated camp list report.
     */
    public String generateCampListReport(List<Camp> camps, ReportOutputType reportOutputType) {
        String header;
        switch (reportOutputType) {
            case TXT:
                header = "Camp List Report:";
                this.reportContent.append(header + "\n");

                camps.forEach(camp -> {
                    this.reportContent.append("Camp Id: " + camp.getCampId() + "\n");
                    this.reportContent.append("Camp Name: " + camp.getName() + "\n");
                    this.reportContent.append("Start Date: " + camp.getStartDate() + "\n");
                    this.reportContent.append("Location: " + camp.getLocation() + "\n\n");
                });
                break;
            case CSV:
                header = "Camp Name,Start Date,Location";
                this.reportContent.append(header).append("\n");

                camps.forEach(camp -> {
                    this.reportContent.append(
                            formatCsvField(camp.getName()) + "," + 
                            formatCsvField(camp.getStartDate().toString()) + "," + 
                            formatCsvField(camp.getLocation()) + "\n"
                            );  
                });
                break;
            default:
                break;
        }
        return reportContent.toString();
    }

    /**
     * Generate a committee performance report based on the specified report output
     * type.
     *
     * @param camps            The list of camps to include in the report.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @return The generated committee performance report.
     */
    public String generateCampCommitteePerformanceReport(List<Camp> camps, ReportOutputType reportOutputType) {
        String header;
        switch (reportOutputType) {
            case TXT:
                header = "Camp Committee Performance Report:";
                this.reportContent.append(header + "\n");

                camps.forEach(camp -> {
                    List<Student> committeeMembers = getCommitteeMembers(camp);
                    this.reportContent.append("Camp Id: " + camp.getCampId() + "\n");
                    committeeMembers.forEach(member -> {
                        this.reportContent.append("Committee Member Name: " + member.getName() + "\n");
                        this.reportContent.append("Committee Points: " + member.getPoints() + "\n");
                    });
                    this.reportContent.append("\n");
                });
                break;
            case CSV:
                header = "Camp Name,Committee Member Name,Committee Points";
                this.reportContent.append(header + "\n");

                camps.forEach(camp -> {
                    String campName = formatCsvField(camp.getName());
                    List<Student> committeeMembers = getCommitteeMembers(camp);

                    committeeMembers.forEach(member -> {
                        this.reportContent.append(
                            campName + "," +
                            formatCsvField(member.getName()) + "," +
                            member.getPoints() + "\n"
                        );
                    });
                });
                break;

            default:
                break;
        }
        return reportContent.toString();
    }

    /**
     * Generate a Enquiry report based on the specified report output type.
     *
     * @param camps            The list of camps to include in the report.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @return The generated Enquiry report.
     */
    public String generateEnquiryReport(List<Camp> camps, ReportOutputType reportOutputType) {
        String header;
        switch (reportOutputType) {
            case TXT:
                header = "Enquiry Report:";
                this.reportContent.append(header).append("\n");

                camps.forEach(camp -> {
                    String campName = camp.getName();

                    List<Enquiry> enquiries = getEnquiries(camp);
                    this.reportContent.append("Camp Name: " + campName).append("\n");

                    enquiries.forEach(enquiry -> {
                        this.reportContent.append("Enquiry Id: " + enquiry.getEnquiryId()).append("\n");
                        this.reportContent.append("Creator Id: " + enquiry.getCreatorId()).append("\n");
                        this.reportContent.append("Responder Id: " + enquiry.getResponderId()).append("\n");
                        this.reportContent.append("Message: " + enquiry.getMessage()).append("\n");
                        this.reportContent.append("Reply Name: " + enquiry.getReply()).append("\n");
                        this.reportContent.append("Enquiry status: " + enquiry.getStatus()).append("\n");
                    });
                    this.reportContent.append("\n");
                });
                break;
            case CSV:
                header = "Camp Name,Enquiry Id,Creator Id,Message";
                this.reportContent.append(header).append("\n");

                camps.forEach(camp -> {
                    String campName = formatCsvField(camp.getName());

                    List<Enquiry> enquiries = getEnquiries(camp);

                    enquiries.forEach(enquiry -> {
                        this.reportContent.append(
                            campName + "," +
                            formatCsvField(enquiry.getEnquiryId()) + "," +
                            formatCsvField(enquiry.getCreatorId()) + "," +
                            formatCsvField(enquiry.getMessage()) + "\n"
                        );
                    });
                    this.reportContent.append("\n");

                });
                break;

            default:
                break;
        }
        return reportContent.toString();
    }

    /**
     * Generate a Suggestion report based on the specified report output type.
     *
     * @param camps            The list of camps to include in the report.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @return The generated Suggestion report.
     */
    public String generateSuggestionReport(List<Camp> camps, ReportOutputType reportOutputType) {
        String header;
        switch (reportOutputType) {
            case TXT:
                header = "Suggestion Report:";
                this.reportContent.append(header).append("\n");

                camps.forEach(camp -> {
                    String campName = camp.getName();

                    List<Suggestion> suggestions = getSuggestions(camp);
                    this.reportContent.append("Camp Name: " + campName).append("\n");

                    suggestions.forEach(suggestion -> {
                        this.reportContent.append("Suggestion Id: " + suggestion.getSuggestionId() + "\n");
                        this.reportContent.append("Creator Id: " + suggestion.getCreatorId() + "\n");
                        this.reportContent.append("Responder Id: " + suggestion.getResponderId() + "\n");
                        this.reportContent.append("Message: " + suggestion.getMessage() + "\n");
                        this.reportContent.append("Suggestion status: " + suggestion.getStatus().getSuggestionStatus() + "\n");
                    });
                    this.reportContent.append("\n");
                });
                break;
            case CSV:
                header = "Camp Name,Suggestion Id,Creator Id,Message";
                this.reportContent.append(header).append("\n");

                camps.forEach(camp -> {
                    String campName = formatCsvField(camp.getName());

                    List<Suggestion> suggestions = getSuggestions(camp);

                    suggestions.forEach(suggestion -> {
                        this.reportContent.append(
                            campName + "," +
                            formatCsvField(suggestion.getSuggestionId()) + "," +
                            formatCsvField(suggestion.getCreatorId()) + "," +
                            formatCsvField(suggestion.getMessage()) + "," +
                            suggestion.getStatus().getSuggestionStatus() + "\n"
                        );
                    });
                    this.reportContent.append("\n");

                });
                break;

            default:
                break;
        }
        return reportContent.toString();
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

    /**
     * Helper method to retrieve camp enquiries
     *
     * @param camp The camp for which to retrieve enquiries.
     * @return A list of enquiries.
     */
    private List<Enquiry> getEnquiries(Camp camp) {
        ArrayList<String> enqIds = camp.getEnquiryIds();
        List<Enquiry> enquiries = new ArrayList<>();

        for (String id : enqIds) {
            enquiries.add(EnquiryController.getEnquiryById(id));
        }
        return enquiries;
    }

    /**
     * Helper method to retrieve camp Suggestions
     *
     * @param camp The camp for which to retrieve suggestions.
     * @return A list of Suggestions.
     */
    private List<Suggestion> getSuggestions(Camp camp) {
        ArrayList<String> sugIds = camp.getSuggestionIds();
        List<Suggestion> suggestions = new ArrayList<>();

        for (String id : sugIds) {
            suggestions.add(SuggestionController.getSuggestionById(id));
        }
        return suggestions;
    }

    /**
     * Helper to format a field for CSV output.
     * For dealing with special characters.
     *
     * @param field The field to format.
     * @return The properly formatted field.
     */
    private String formatCsvField(String field) {
        // If the field contains a special character or whitespace, enclose it within
        // double quotes
        // Plus if the field contains double quotes, escape them
        if (field.contains(",") || field.contains("\n") || field.contains("\r") || field.contains("\t")
                || field.contains("\"")) {
            field = field.replace("\"", "\"\"");
            return "\"" + field + "\"";
        }
        return field;
    }
}
