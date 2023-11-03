package report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtWriter {
    public static void writeTextReportToFile(String content, String fileName) {
        // Writing the text report to a file
        try {
            // The code to write to a text file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
            writer.close();
            System.out.println("Text report written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
