package report.enums;

/**
 * Represents different types of report files.
 * 
 * @author Choh Lit Han Owen
 * @version 1.0
 * @since 2023-11-03
 */
public enum ReportOutputType {
    TXT("txt"),
    CSV("csv");

    private final String reportOutputTypeStr;

    /**
     * Constructor for ReportOutputType enum.
     * 
     * @param reportOutputTypeStr The file extension associated with the report output type.
     */
    private ReportOutputType(String reportOutputTypeStr) {
        this.reportOutputTypeStr = reportOutputTypeStr;
    }

    /**
     * Get the file extension associated with the report output type.
     * 
     * @return The file extension string.
     */
    public String getReportOutputTypeStr() {
        return reportOutputTypeStr;
    }
}
