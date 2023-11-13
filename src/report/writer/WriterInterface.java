package report.writer;

/**
 * Defines methods for classes that write reports to files.
 * Implementing classes should provide methods to write report data to specified files.
 * 
 * @since 2023-11-03
 */
public interface WriterInterface {
    /**
     * Writes report data to a specified file.
     * 
     * @param csvData The formatted report data to be written to the file.
     * @param filePath The path to the file where the report data will be saved.
     */
    public static void writeReportToFile(String csvData, String filePath) {};
}