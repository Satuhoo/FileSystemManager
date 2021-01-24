package fileManipulation;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logging {

    public void writeToLog (LocalDateTime time, Long start, Long end, String logText) {
        try {
            FileWriter myWriter = new FileWriter("../src/log/log.txt", true); //True is responsible for being able to add some content to the end of the file
            myWriter.write("\n" + time + ": " + logText + " The function took " + (end - start) + "ms to execute.");
            myWriter.write("\n");
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    } 
}
