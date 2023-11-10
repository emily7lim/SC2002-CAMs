package report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides utility methods to write CSV reports to files.
 * 
 * @author Choh Lit Han Owen
 * @version 1.0
 * @since 2023-11-03
 */
public class CsvWriter {
    /**
     * Writes the provided CSV data to a specified file.
     * 
     * @param csvData The CSV data to be written to the file.
     * @param filePath The path to the file where the CSV data will be saved.
     */
    public static void writeCSVReportToFile(String csvData, String filePath) {
        // Writing the CSV report to a file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            
            writer.write(csvData);
            
            writer.close();
            System.out.println("CSV report written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
