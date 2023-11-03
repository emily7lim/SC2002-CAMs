package report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import model.Camp;

public class ReportController {
    private static final String folder = "reportfiles";
    private Report report;
    private ReportType reportType;
    private String baseFilename;

    public ReportController(){
        this.report = new Report();
        this.baseFilename = "report";
        this.reportType = ReportType.TXT;
    }
    public ReportController(ReportType reportType){
        this.report = new Report();
        this.baseFilename = "report";
        this.reportType = reportType;
    }

    public String generateUniqueFilename(){ return this.generateUniqueFilename(this.baseFilename, this.reportType); };
    public String generateUniqueFilename(String baseFilename){ return this.generateUniqueFilename(baseFilename, this.reportType); };
    public String generateUniqueFilename(String baseFilename, ReportType reportType) {
        if(baseFilename.length() == 0) baseFilename = this.baseFilename;
        String extension = "." + reportType.getReportTypeStr();
        String filePath = getFilePath(this.baseFilename + extension);
        int counter = 1;

        while (fileExists(filePath)) {
            filePath = getFilePath(this.baseFilename + "_" + counter + extension);
            counter++;
        }
        return filePath;
    }
    public boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    public static String getFilePath(String fileName) {
        return "./src/report/" + folder + "/" + fileName;
    }

    public void writeTextReportToFile(String content, String fileName) {
        // Writing the text report to a file
        TxtWriter.writeTextReportToFile(content, fileName);
    }

    public void writeCSVReportToFile(List<String[]> csvData, String fileName) {
        // Writing the CSV report to a file
        CsvWriter.writeCSVReportToFile(csvData, fileName);
    }

    public void generateAndWriteReports(List<Camp> camps) { this.generateAndWriteReports(camps, this.baseFilename); }
    public void generateAndWriteReports(List<Camp> camps, String fileName) {
        String reportContent = report.generateCampListReport(camps, this.reportType);
        String filePath = generateUniqueFilename(fileName);
        
        switch (this.reportType) {
            case TXT:
                writeTextReportToFile(reportContent, filePath);
                break;
            case CSV:
                List<String[]> csvContent = new ArrayList<>();
                // Split reportContent into lines and add each line as a String[] to csvContent
                String[] lines = reportContent.split("\n");
                for (String line : lines) {
                    csvContent.add(line.split(","));
                }
                writeCSVReportToFile(csvContent, filePath);
                break;
        
            default:
                break;
        }
    }
}
