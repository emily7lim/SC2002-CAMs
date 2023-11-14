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
import view.FilterObj;

/**
 * Generates report content for different report types,
 * Only has static methods
 * 
 * @author Choh Lit Han Owen
 * @version 1.2
 * @since 2023-11-03
 */
public class Report {
    /**
     * Generate a camp details report based on the specified report output type.
     *
     * @param camps            The list of camps to include in the report.
     * @param filterObj        The filter options for the report.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @return The generated camp details report.
     */
    public static String generateCampDetailsReport(List<Camp> camps, FilterObj filterObj,
            ReportOutputType reportOutputType) {
        // no need to check filterObj since controller checked
        StringBuilder reportContent = new StringBuilder();
        String header;
        switch (reportOutputType) {
            case TXT:
                header = "Camp List Report:";
                reportContent.append(header + "\n");

                camps.forEach(camp -> {
                    reportContent.append("Camp Id: " + camp.getCampId() + "\n");
                    reportContent.append("Camp Name: " + camp.getName() + "\n");
                    reportContent.append("Staff IC: " + camp.getStaffInCharge() + "\n");
                    reportContent.append("Description: " + camp.getDescription() + "\n\n");

                    // if user wants attendee list in report
                    if (filterObj.isSelectedAttendee()) {
                        reportContent.append("Participant List:\n");
                        List<Student> participants = getParticipantMembers(camp);
                        int i = 1;
                        for (Student student : participants) {
                            reportContent.append("Participant " + i + "Id: " + student.getUserId() + "\n");
                            reportContent.append("Participant " + i + "Name: " + student.getName() + "\n");
                            reportContent.append("Participant " + i + "Faculty: " + student.getFaculty() + "\n");
                            reportContent.append("Participant " + i++ + "Role: " + student.getRole() + "\n");
                        }
                    }

                    // if user wants committee list in report
                    if (filterObj.isSelectedCampCommittee()) {
                        reportContent.append("Committee List:\n");
                        List<Student> commMembers = getCommitteeMembers(camp);
                        int i = 1;
                        for (Student student : commMembers) {
                            reportContent.append("Committee member " + i + "Id: " + student.getUserId() + "\n");
                            reportContent.append("Committee member " + i + "Name: " + student.getName() + "\n");
                            reportContent.append("Committee member " + i + "points: " + student.getPoints() + "\n");
                            reportContent.append("Committee member " + i + "Faculty: " + student.getFaculty() + "\n");
                            reportContent.append("Committee member " + i++ + "Role: " + student.getRole() + "\n");
                        }
                    }
                });
                break;
            case CSV:
                header = "Camp Id,Camp Name,Staff IC,Description"; // camp values to print
                if (filterObj.isSelectedAttendee() || filterObj.isSelectedCampCommittee())
                    header.concat(",Student Id,Student Name,Student Faculty,Student Role,Points"); // student values to
                                                                                                   // print
                reportContent.append(header + "\n");

                camps.forEach(camp -> {
                    // same starting values for different students
                    String campStr = formatCsvField(camp.getCampId()) + "," +
                            formatCsvField(camp.getName()) + "," +
                            formatCsvField(camp.getStaffInCharge()) + "," +
                            formatCsvField(camp.getDescription());

                    // parts for filtering
                    // if user wants attendee list in report
                    if (filterObj.isSelectedAttendee()) {
                        List<Student> participants = getParticipantMembers(camp);
                        csvStudentFormatter(reportContent, campStr, participants);
                    }

                    // if user wants committee list in report
                    if (filterObj.isSelectedCampCommittee()) {
                        List<Student> commMembers = getCommitteeMembers(camp);
                        csvStudentFormatter(reportContent, campStr, commMembers);
                    }
                    // next camp
                    reportContent.append("\n");
                });
                break;
            default:
                break;
        }
        return reportContent.toString();
    }

    /**
     * Helper function for generateCampListReport csv part.
     * Formats student details and appends them to the report content.
     *
     * @param reportContent The StringBuilder to append the formatted details.
     * @param campStr       The formatted camp details.
     * @param students      The list of students to include in the report.
     */
    private static void csvStudentFormatter(StringBuilder reportContent, String campStr, List<Student> stds) {
        for (Student student : stds) {
            reportContent.append(campStr);

            reportContent.append(formatCsvField(student.getUserId()) + "," +
                    formatCsvField(student.getName()) + "," +
                    formatCsvField(student.getFaculty().getFaculty()) + "," +
                    formatCsvField(student.getRole().getRole()) + "," +
                    student.getPoints() + "\n");
        }
    }

    /**
     * Generate a committee performance report based on the specified report output
     * type.
     *
     * @param camps            The list of camps to include in the report.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @return The generated committee performance report.
     */
    public static String generateCampCommitteePerformanceReport(List<Camp> camps, ReportOutputType reportOutputType) {
        StringBuilder reportContent = new StringBuilder();
        String header;
        switch (reportOutputType) {
            case TXT:
                header = "Camp Committee Performance Report:";
                reportContent.append(header + "\n");

                camps.forEach(camp -> {
                    List<Student> committeeMembers = getCommitteeMembers(camp);
                    reportContent.append("Camp Id: " + camp.getCampId() + "\n");
                    committeeMembers.forEach(member -> {
                        reportContent.append("Committee Member Name: " + member.getName() + "\n");
                        reportContent.append("Committee Points: " + member.getPoints() + "\n");
                    });
                    reportContent.append("\n");
                });
                break;
            case CSV:
                header = "Camp Name,Committee Member Name,Committee Points";
                reportContent.append(header + "\n");

                camps.forEach(camp -> {
                    String campName = formatCsvField(camp.getName());
                    List<Student> committeeMembers = getCommitteeMembers(camp);

                    committeeMembers.forEach(member -> {
                        reportContent.append(
                                campName + "," +
                                        formatCsvField(member.getName()) + "," +
                                        member.getPoints() + "\n");
                    });
                });
                break;

            default:
                break;
        }
        return reportContent.toString();
    }

    /**
     * Generate an Enquiry report based on the specified report output type.
     *
     * @param camps            The list of camps to include in the report.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @return The generated Enquiry report.
     */
    public static String generateEnquiryReport(List<Camp> camps, ReportOutputType reportOutputType) {
        StringBuilder reportContent = new StringBuilder();
        String header;
        switch (reportOutputType) {
            case TXT:
                header = "Enquiry Report:";
                reportContent.append(header).append("\n");

                camps.forEach(camp -> {
                    String campName = camp.getName();

                    List<Enquiry> enquiries = getEnquiries(camp);
                    reportContent.append("Camp Name: " + campName).append("\n");

                    enquiries.forEach(enquiry -> {
                        reportContent.append("Enquiry Id: " + enquiry.getEnquiryId()).append("\n");
                        reportContent.append("Creator Id: " + enquiry.getCreatorId()).append("\n");
                        reportContent.append("Responder Id: " + enquiry.getResponderId()).append("\n");
                        reportContent.append("Message: " + enquiry.getMessage()).append("\n");
                        reportContent.append("Reply: " + enquiry.getReply()).append("\n");
                        reportContent.append("Enquiry status: " + enquiry.getStatus()).append("\n");
                    });
                    reportContent.append("\n");
                });
                break;
            case CSV:
                header = "Camp Name,Enquiry Id,Creator Id,Responder Id,Message,Reply,Enquiry status";
                reportContent.append(header).append("\n");

                camps.forEach(camp -> {
                    String campName = formatCsvField(camp.getName());

                    List<Enquiry> enquiries = getEnquiries(camp);

                    enquiries.forEach(enquiry -> {
                        reportContent.append(
                                campName + "," +
                                        formatCsvField(enquiry.getEnquiryId()) + "," +
                                        formatCsvField(enquiry.getCreatorId()) + "," +
                                        formatCsvField(enquiry.getResponderId()) + "," +
                                        formatCsvField(enquiry.getMessage()) + "," +
                                        formatCsvField(enquiry.getReply()) + "," +
                                        formatCsvField(enquiry.getStatus().getEnquiryStatus()) + "\n");
                    });
                    reportContent.append("\n");

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
    public static String generateSuggestionReport(List<Camp> camps, ReportOutputType reportOutputType) {
        StringBuilder reportContent = new StringBuilder();
        String header;
        switch (reportOutputType) {
            case TXT:
                header = "Suggestion Report:";
                reportContent.append(header).append("\n");

                camps.forEach(camp -> {
                    String campName = camp.getName();

                    List<Suggestion> suggestions = getSuggestions(camp);
                    reportContent.append("Camp Name: " + campName).append("\n");

                    suggestions.forEach(suggestion -> {
                        reportContent.append("Suggestion Id: " + suggestion.getSuggestionId()).append("\n");
                        reportContent.append("Creator Id: " + suggestion.getCreatorId()).append("\n");
                        reportContent.append("Responder Id: " + suggestion.getResponderId()).append("\n");
                        reportContent.append("Message: " + suggestion.getMessage()).append("\n");
                        reportContent.append("Suggestion status: " + suggestion.getStatus()).append("\n");
                    });
                    reportContent.append("\n");
                });
                break;
            case CSV:
                header = "Camp Name,Suggestion Id,Creator Id,Responder Id,Message,Suggestion status";
                reportContent.append(header).append("\n");

                camps.forEach(camp -> {
                    String campName = formatCsvField(camp.getName());

                    List<Suggestion> suggestions = getSuggestions(camp);

                    suggestions.forEach(suggestion -> {
                        reportContent.append(
                                campName + "," +
                                        formatCsvField(suggestion.getSuggestionId()) + "," +
                                        formatCsvField(suggestion.getCreatorId()) + "," +
                                        formatCsvField(suggestion.getResponderId()) + "," +
                                        formatCsvField(suggestion.getMessage()) + "," +
                                        formatCsvField(suggestion.getStatus().getSuggestionStatus()) + "\n");
                    });
                    reportContent.append("\n");

                });
                break;

            default:
                break;
        }
        return reportContent.toString();
    }

    /**
     * Helper method to retrieve camp committee members for a camp.
     *
     * @param camp The camp for which to retrieve committee members.
     * @return A list of committee members.
     */
    private static List<Student> getCommitteeMembers(Camp camp) {
        ArrayList<String> commIds = camp.getCommitteeIds();
        List<Student> committeeMembers = new ArrayList<>();

        for (String id : commIds) {
            committeeMembers.add(StudentController.getStudentByUserId(id));
        }
        return committeeMembers;
    }

    /**
     * Helper method to retrieve camp participant members for a camp.
     *
     * @param camp The camp for which to retrieve participants.
     * @return A list of participants.
     */
    private static List<Student> getParticipantMembers(Camp camp) {
        ArrayList<String> parIds = camp.getParticipantIds();
        List<Student> participants = new ArrayList<>();

        for (String id : parIds) {
            participants.add(StudentController.getStudentByUserId(id));
        }
        return participants;
    }

    /**
     * Helper method to retrieve camp enquiries.
     *
     * @param camp The camp for which to retrieve enquiries.
     * @return A list of enquiries.
     */
    private static List<Enquiry> getEnquiries(Camp camp) {
        ArrayList<String> enqIds = camp.getEnquiryIds();
        List<Enquiry> enquiries = new ArrayList<>();

        for (String id : enqIds) {
            enquiries.add(EnquiryController.getEnquiryById(id));
        }
        return enquiries;
    }

    /**
     * Helper method to retrieve camp suggestions.
     *
     * @param camp The camp for which to retrieve suggestions.
     * @return A list of suggestions.
     */
    private static List<Suggestion> getSuggestions(Camp camp) {
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
    private static String formatCsvField(String field) {
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
