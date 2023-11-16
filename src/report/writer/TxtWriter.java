package report.writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides utility methods to write text reports to files.
 * 
 * @author Choh Lit Han Owen
 * @version 1.1
 * @since 2023-11-03
 */
public class TxtWriter implements WriterInterface {
    /**
     * Writes the provided text content to a specified file.
     * 
     * @param content  The text content to be written to the file.
     * @param filePath The path to the file where the content will be saved.
     */
    public static void writeReportToFile(String content, String filePath) {
        // Writing the text report to a file
        try {
            FileWriter writer = new FileWriter(filePath); // new BufferedWriter(new FileWriter(filePath));
            writer.write(content);
            writer.close();
            System.out.println("Text report written to " + filePath);
        } catch (IOException e) {
            System.out.println("error occurred when writing to file");
            e.printStackTrace();
        }
    }
}
