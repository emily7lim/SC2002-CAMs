package report.enums;

/**
 * Represents different types of reports.
 * 
 * @author Owen
 * @version 1.1
 * @since 2023-11-03
 */
public enum ReportType {
    /** Camp Details Report Type */
    CAMP_DETAILS_REPORT("Camp Details Report"),
    /** Performance Report Type */
    PERFORMANCE_REPORT("Performance Report"),
    /** Enquiries Report Type */
    ENQUIRIES_REPORT("Enquiries Report"),
    /** Sugestions Report Type */
    SUGGESTION_REPORT("Suggestion Report");

    /** The display name of the report type */
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
