package report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    public static void writeCSVReportToFile(List<String[]> csvData, String fileName) {
        // Writing the CSV report to a file
        try {
            // The code to write to a CSV file remains the same
            // ...
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (String[] row : csvData) {
                for (int i = 0; i < row.length; i++) {
                    writer.write(row[i]);
                    if (i < row.length - 1) {
                        writer.write(",");
                    }
                }
                writer.newLine();
            }

            writer.close();
            System.out.println("CSV report written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
