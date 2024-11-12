package design;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


/**
 * Read from last using RandomAccessFile
 * */
public class UnixTail {

    /**
     * Usages:
     * tail -n 20 example.txt : View the last 20 lines of a file:
     * tail -f /var/log/nginx/access.log : Monitor a growing log file:
     * tail -f --pid=1234 /var/log/app.log : Follow a file and stop when a process ends:
     * tail -c 100 example.txt : View the last 100 bytes of a file:
     *
     *
     * */

    public static void main(String[] args) {
        int linesToRead = 6;
        try(RandomAccessFile file = new RandomAccessFile("design/UnixTail.java", "rw")) {
            long cur = file.length()-1;
            StringBuilder result = new StringBuilder();
            int linesRead = 0;
            while(cur >= 0) {
                file.seek(cur);
                char c = (char) file.read();
                if(System.lineSeparator().equals(String.valueOf(c))) {
                    linesRead++;
                    if(linesRead > linesToRead) break;
                }
                result.append(c);
                cur--;
            }
            System.out.println(result.reverse());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void tailWithProcessAlive(){
        int linesToRead = 6;
        String filePath = "/var/log/app.log"; // Replace with the file you want to tail
        long pidToMonitor = 1234; // Replace with the PID of the process to monitor

        // Monitor the process and stop if it terminates
        Optional<ProcessHandle> processHandle = ProcessHandle.of(pidToMonitor);
        if (processHandle.isEmpty()) {
            System.err.println("No process found with PID: " + pidToMonitor);
            return;
        }

        ProcessHandle handle = processHandle.get();
        System.out.println("Monitoring process with PID: " + pidToMonitor);

        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            long filePointer = file.length();

            while (handle.isAlive()) { // Continue while the monitored process is alive
                long currentLength = file.length();

                // Check if the file has grown
                if (currentLength > filePointer) {
                    file.seek(filePointer); // Move to the last read position
                    StringBuilder result = new StringBuilder();
                    int linesRead = 0;

                    // Read new content
                    long cur = currentLength - 1;
                    file.seek(cur);
                    while (cur >= filePointer) {
                        char c = (char) file.read();
                        if (System.lineSeparator().equals(String.valueOf(c))) {
                            linesRead++;
                            if (linesRead > linesToRead) break;
                        }
                        result.append(c);
                        cur--;
                        file.seek(cur);
                    }

                    System.out.print(result.reverse()); // Output the new content
                    filePointer = currentLength; // Update the file pointer
                }

                // Wait for a short time before checking again
                Thread.sleep(500); // Adjust sleep time as needed
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Exit message
        System.out.println("Monitored process ended. Exiting tail...");

    }
}

/**
 *
 * Read from front using BufferedReader
 * */
class TailCommand {

    public static void main(String[] args) {
        // Check if file path and number of lines are provided
        if (args.length < 2) {
            System.out.println("Usage: java TailCommand <file_path> <number_of_lines>");
            return;
        }

        String filePath = args[0];
        int numLines;

        try {
            numLines = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("The number of lines must be a valid integer.");
            return;
        }

        try {
            List<String> lastLines = tail(filePath, numLines);
            lastLines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Reads the last n lines of a file.
     *
     * @param filePath The path of the file.
     * @param numLines The number of lines to read.
     * @return A list of the last n lines.
     * @throws IOException If an error occurs while reading the file.
     */
    public static List<String> tail(String filePath, int numLines) throws IOException {
        Deque<String> lines = new ArrayDeque<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (lines.size() == numLines) {
                    lines.pollFirst(); // Remove the oldest line
                }
                lines.addLast(line);
            }
        }

        return new ArrayList<>(lines);
    }
}

