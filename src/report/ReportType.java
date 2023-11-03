package report;

public enum ReportType {
    TXT ("txt"),
    CSV ("csv");

    private final String reporttypestr;

    private ReportType(String reporttypestr) {
        this.reporttypestr = reporttypestr;
    }

    public String getReportTypeStr() {
        return reporttypestr;
    }
}
