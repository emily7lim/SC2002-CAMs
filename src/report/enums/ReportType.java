package report.enums;

/**
 * Represents different types of reports.
 * 
 * @author Owen
 * @version 1.1
 * @since 2023-11-03
 */
public enum ReportType {
    CAMP_DETAILS_REPORT("Camp Details Report"),
    PERFORMANCE_REPORT("Performance Report"),
    ENQUIRIES_REPORT("Enquiries Report"),
    SUGGESTION_REPORT("Suggestion Report")
    ;

    private final String reportTypeName;

    /**
     * Constructor for ReportType enum.
     * 
     * @param reportTypeName The display name of the report type.
     */
    private ReportType(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    /**
     * Get the display name of the report type.
     * 
     * @return The report type's display name.
     */
    public String getReportTypeName() {
        return reportTypeName;
    }
}
