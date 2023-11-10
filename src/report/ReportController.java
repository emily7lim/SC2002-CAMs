package report;

import java.io.File;
import java.util.List;

import model.Camp;
import report.enums.ReportOutputType;
import report.enums.ReportType;

/**
 * Manages the generation and writing of various reports, 
 * such as camp lists and performance reports.
 * 
 * @author Choh Lit Han Owen
 * @version 1.1
 * @since 2023-11-03
 */
public class ReportController {
    private static final String folder = "reportfiles";
    private Report report;
    private ReportOutputType reportOutputType;
    private String baseFilename;

    public ReportController(){
        this.report = new Report();
        this.baseFilename = "report";
        this.reportOutputType = ReportOutputType.TXT;
    }
    public ReportController(ReportOutputType reportOutputType){
        this.report = new Report();
        this.baseFilename = "report";
        this.reportOutputType = reportOutputType;
    }

    /**
     * Overload of the generateUniqueFilename method with default values set in constructor.
     * 
     * @see #generateUniqueFilename(String baseFilename, ReportOutputType reportOutputType)
     * 
     * @return A unique filename generated based on the default baseFilename and reportOutputType.
     */
    public String generateUniqueFilename(){ return this.generateUniqueFilename(this.baseFilename, this.reportOutputType); };
    /**
     * Overload of the generateUniqueFilename method with a default reportOutputType.
     * 
     * @param baseFilename The string parameter for the report file name.
     * 
     * @see #generateUniqueFilename(String baseFilename, ReportOutputType reportOutputType)
     * 
     * @return A unique filename generated based on the specified baseFilename and the default reportOutputType.
     */
    public String generateUniqueFilename(String baseFilename){ return this.generateUniqueFilename(baseFilename, this.reportOutputType); };

    /**
     * Generate a unique filename, accounting for conflicts where the file already exists.
     * 
     * @param baseFilename The string parameter for the report file name.
     * @param reportOutputType The ReportOutputType parameter from enum ReportOutputType.
     * 
     * @return The final name of the report file after handling conflicts.
     */
    public String generateUniqueFilename(String baseFilename, ReportOutputType reportOutputType) {
        if(baseFilename.length() == 0) baseFilename = this.baseFilename;
        String extension = "." + reportOutputType.getReportOutputTypeStr();
        String filePath = getFilePath(this.baseFilename + extension);
        int counter = 1;

        while (fileExists(filePath)) {
            filePath = getFilePath(this.baseFilename + "_" + counter + extension);
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
    public boolean fileExists(String filename) {
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
    public static String getFilePath(String fileName) {
        return "./src/report/" + folder + "/" + fileName;
    }

    /**
     * Write the report details to a text file using the TxtWriter object.
     * 
     * @param content The string parameter for the data to write.
     * @param filePath The string parameter for the file path to save the report to.
     */
    public void writeTextReportToFile(String content, String filePath) {
        // Writing the text report to a file
        TxtWriter.writeTextReportToFile(content, filePath);
    }
    
    /**
     * Write the report details to a CSV file using the CsvWriter object.
     * 
     * @param csvData The string parameter for the data to write.
     * @param fileName The string parameter for the name of the CSV file.
     */
    public void writeCSVReportToFile(String csvData, String fileName) {
        // Writing the CSV report to a file
        CsvWriter.writeCSVReportToFile(csvData, fileName);
    }

    /**
     * Generate and write reports for a list of camps and a specific report type.
     * 
     * @param camps The list of camps for which to generate and write reports.
     * @param reportType The specific report type to generate (e.g., CAMP_LIST or PERFORMANCE_REPORT).
     */
    public void generateAndWriteReports(List<Camp> camps, ReportType reportType) { this.generateAndWriteReports(camps, reportType, this.baseFilename); }

    /**
     * Generate and write reports for a list of camps, a specific report type, and a custom file name.
     * 
     * @param camps The list of camps for which to generate and write reports.
     * @param reportType The specific report type to generate (e.g., CAMP_LIST or PERFORMANCE_REPORT).
     * @param fileName The custom file name for the report.
     */
    public void generateAndWriteReports(List<Camp> camps, ReportType reportType, String fileName) {
        String reportContent = "";

        switch (reportType) {
            case CAMP_LIST:
                reportContent = report.generateCampListReport(camps, this.reportOutputType);
                break;
            case PERFORMANCE_REPORT:
                reportContent = report.generateCampCommitteePerformanceReport(camps, this.reportOutputType);
                break;
            default:
                break;
        }

        String filePath = generateUniqueFilename(fileName);
        
        switch (this.reportOutputType) {
            case TXT:
                writeTextReportToFile(reportContent, filePath);
                break;
            case CSV:
                writeCSVReportToFile(reportContent, filePath);
                break;
        
            default:
                break;
        }
    }
}
