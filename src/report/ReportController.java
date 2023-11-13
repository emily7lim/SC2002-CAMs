package report;

import java.io.File;
import java.util.List;

import model.Camp;
import report.enums.ReportOutputType;
import report.enums.ReportType;
import report.writer.CsvWriter;
import report.writer.TxtWriter;

/**
 * Manages the generation and writing of various reports, 
 * such as camp lists and performance reports.
 * 
 * @author Choh Lit Han Owen
 * @version 1.2
 * @since 2023-11-03
 */
public class ReportController {
    private static final String basePath = "./src/report/";
    // the folder to store reports into 
    private static final String folder = "reportfiles";
    // Object to get and format the details required
    private Report report;
    // default file output if not specified in methods
    private ReportOutputType reportOutputType;
    // default file name used if not specified in methods
    private String baseFilename; 

    /**
     * Default constructor for the ReportController.
     * Initializes the report, baseFilename, and reportOutputType with default values.
     */
    public ReportController(){
        this.report = new Report();
        this.baseFilename = "report";
        this.reportOutputType = ReportOutputType.TXT;
    }

    /**
     * Constructor with a specified report output type.
     * Initializes the report, baseFilename with default values, 
     * and reportOutputType with the specified value.
     * 
     * @param reportOutputType The specified report output type (e.g., TXT or CSV).
     */
    public ReportController(ReportOutputType reportOutputType){
        this.report = new Report();
        this.baseFilename = "report";
        this.reportOutputType = reportOutputType;
    }

    /**
     * Overload of the generateUniqueFilename method with all default values set in constructor.
     * 
     * @see #generateUniqueFilename(String baseFilename, ReportOutputType reportOutputType)
     * 
     * @return A unique filename generated based on the default baseFilename and reportOutputType.
     */
    public String generateUniqueFilename(){ return this.generateUniqueFilename(this.baseFilename, this.reportOutputType); };
    /**
     * Overload of the generateUniqueFilename method with default reportOutputType.
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
     * Conflicts means the name will become "filname" + "_number"
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
    private static String getFilePath(String fileName) {
        return basePath + folder + "/" + fileName;
    }

    /**
     * Write the report details to a file using the appropriate writer.
     * Chooses output file type using instance variable
     * 
     * @param data The string parameter for the data to write.
     * @param filePath The string parameter for the file path to save the report to.
     */
    public void writeReportToFile(String content, String filePath) {
        // Determine the writer method based on the report type
        switch (this.reportOutputType) {
            case TXT:
                TxtWriter.writeReportToFile(content, filePath);
                break;
            case CSV:
                CsvWriter.writeReportToFile(content, filePath);
                break;
            default:
                System.out.println("Unsupported report type: " + this.reportOutputType);
                break;
        }
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
        
        writeReportToFile(reportContent, filePath);
    }
}
