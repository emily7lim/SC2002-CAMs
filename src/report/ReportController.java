package report;

import java.io.File;
import java.util.List;

import model.Camp;
import report.enums.ReportOutputType;
import report.enums.ReportType;
import report.writer.CsvWriter;
import report.writer.TxtWriter;
import view.FilterObj;

/**
 * Manages the generation and writing of various reports,
 * such as camp lists and performance reports.
 * Only has static methods
 * 
 * @author Choh Lit Han Owen
 * @version 1.3
 * @since 2023-11-03
 */
public class ReportController {
    // relative path
    private static final String basePath = "./src/report/";
    // the folder to store reports into
    private static final String folder = "reportfiles";
    // default file output if not specified in methods
    private static final ReportOutputType reportOutputType = ReportOutputType.TXT;
    // default file name used if not specified in methods
    private static final String baseFilename = "report";

    /**
     * Overload of the generateUniqueFilename method with all default values set in
     * constructor.
     * 
     * @see #generateUniqueFilename(String baseFilename, ReportOutputType
     *      reportOutputType)
     * 
     * @return A unique filename generated based on the default baseFilename and
     *         reportOutputType.
     */
    public static String generateUniqueFilename() {
        return ReportController.generateUniqueFilename(ReportController.baseFilename,
                ReportController.reportOutputType);
    };

    /**
     * Overload of the generateUniqueFilename method with default reportOutputType.
     * 
     * @param baseFilename The string parameter for the report file name.
     * 
     * @see #generateUniqueFilename(String baseFilename, ReportOutputType
     *      reportOutputType)
     * 
     * @return A unique filename generated based on the specified baseFilename and
     *         the default reportOutputType.
     */
    public static String generateUniqueFilename(String baseFilename) {
        return ReportController.generateUniqueFilename(baseFilename, ReportController.reportOutputType);
    };

    /**
     * Generate a unique filename, accounting for conflicts where the file already
     * exists.
     * Conflicts means the name will become "filname" + "_number"
     * 
     * @param baseFilename     The string parameter for the report file name.
     * @param reportOutputType The ReportOutputType parameter from enum
     *                         ReportOutputType.
     * 
     * @return The final name of the report file after handling conflicts.
     */
    public static String generateUniqueFilename(String baseFilename, ReportOutputType reportOutputType) {
        if (baseFilename.length() == 0)
            baseFilename = ReportController.baseFilename;
        String extension = "." + reportOutputType.getReportOutputTypeStr();
        String filePath = getFilePath(ReportController.baseFilename + extension);
        int counter = 1;

        while (fileExists(filePath)) {
            filePath = getFilePath(ReportController.baseFilename + "_" + counter + extension);
            counter++;
        }
        return filePath;
    }

    /**
     * Check if a file with the given name already exists.
     * 
     * @param filename The string parameter for the file name to check.
     * 
     * @return True if the file with the given name exists; otherwise, false.
     */
    public static boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    /**
     * Return the path to the save location for report files.
     * 
     * @param fileName The string parameter for the file name.
     * 
     * @return The full file path of the report file.
     */
    private static String getFilePath(String fileName) {
        return basePath + folder + "/" + fileName;
    }

    /**
     * Write the report details to a file using the appropriate writer.
     * Chooses output file type using instance variable
     * 
     * @param data     The string parameter for the data to write.
     * @param filePath The string parameter for the file path to save the report to.
     */
    public static void writeReportToFile(String content, String filePath) {
        // Determine the writer method based on the report type
        switch (ReportController.reportOutputType) {
            case TXT:
                TxtWriter.writeReportToFile(content, filePath);
                break;
            case CSV:
                CsvWriter.writeReportToFile(content, filePath);
                break;
            default:
                System.out.println("Unsupported report type: " + ReportController.reportOutputType);
                break;
        }
    }

    /**
     * Generate and write reports for a list of camps and a specific report type.
     * 
     * @param camps      The list of camps for which to generate and write reports.
     * @param filterObj  The filter options for the report.
     * @param reportType The specific report type to generate (e.g., CAMP_LIST or
     *                   PERFORMANCE_REPORT).
     * 
     * @see #generateAndWriteReports(List<Camp> camps, FilterObj filterObj,
     *      ReportType reportType, ReportOutputType reportOutputType, String
     *      fileName)
     */
    public static void generateAndWriteReports(List<Camp> camps, FilterObj filterObj, ReportType reportType) {
        ReportController.generateAndWriteReports(camps, filterObj, reportType, ReportController.reportOutputType,
                ReportController.baseFilename);
    }

    /**
     * Generate and write reports for a list of camps and a specific report type.
     * 
     * @param camps            The list of camps for which to generate and write
     *                         reports.
     * @param filterObj        The filter options for the report.
     * @param reportType       The specific report type to generate (e.g., CAMP_LIST
     *                         or PERFORMANCE_REPORT).
     * 
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * 
     * @see #generateAndWriteReports(List<Camp> camps, FilterObj filterObj,
     *      ReportType reportType, ReportOutputType reportOutputType, String
     *      fileName)
     */
    public static void generateAndWriteReports(List<Camp> camps, FilterObj filterObj, ReportType reportType,
            ReportOutputType reportOutputType) {
        ReportController.generateAndWriteReports(camps, filterObj, reportType, reportOutputType,
                ReportController.baseFilename);
    }

    /**
     * Generate and write reports for a list of camps, a specific report type, and a
     * custom file name.
     * 
     * @param camps            The list of camps for which to generate and write
     *                         reports.
     * @param filterObj        The filter options for the report.
     * @param reportType       The specific report type to generate.
     * @param reportOutputType The type of report to generate (TXT or CSV).
     * @param fileName         The custom file name for the report.
     */
    public static void generateAndWriteReports(List<Camp> camps, FilterObj filterObj, ReportType reportType,
            ReportOutputType reportOutputType, String fileName) {
        String reportContent = "";

        switch (reportType) {
            case CAMP_DETAILS_REPORT:
                if (!filterObj.isAnyCase5()) {
                    System.out.println("all filters are false, please check the filterObj");
                    return;
                }
                reportContent = Report.generateCampDetailsReport(camps, filterObj, reportOutputType);
                break;
            case PERFORMANCE_REPORT:
                reportContent = Report.generateCampCommitteePerformanceReport(camps, reportOutputType); // TODO if need
                                                                                                        // filter
                break;
            case ENQUIRIES_REPORT:
                reportContent = Report.generateEnquiryReport(camps, reportOutputType); // TODO if need filter
                break;
            case SUGGESTION_REPORT:
                reportContent = Report.generateEnquiryReport(camps, reportOutputType); // TODO if need filter
                break;
            default:
                break;
        }

        if (reportContent.equals("")) {
            System.out.println("error in making report");
            return;
        }

        String filePath = generateUniqueFilename(fileName);

        writeReportToFile(reportContent, filePath);
    }

}
