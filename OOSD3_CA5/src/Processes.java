import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Processes {
    public static void main(String[] args) {

        String website = "jacksheehy.live"; // Define "jacksheehy.live" as the website to ping
        int numberOfPings = 10; // Define the number of times to ping the website

        ExecutorService servicePool = Executors.newSingleThreadExecutor(); // Create ExecutorService to manage nabage
                                                                           // execution of threads

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "ping", "-n", String.valueOf(numberOfPings),
                website); // Create a new process builder with command from Q1

        try {
            // Start the process
            Process process = processBuilder.start();

            // create a Callable to read the output of the process
            // Passes the input stream of the process as an argument

            Callable<List<String>> processReader = new ProcessReader(process.getInputStream());

            // Submit the Callable to the ExecutorService
            Future<List<String>> future = servicePool.submit(processReader);

            // read the results from the Future object and store it in result
            List<String> result = future.get();

            // For loop to print the result to the console
            for (int i = 0; i < result.size(); i++) {
                System.out.println(result.get(i));
            }

        } catch (Exception e) { // Catch any exceptions
            e.printStackTrace();
        }
    }

    // A static class implementing the Callable interface.

    static class ProcessReader implements Callable<List<String>> {
        private final BufferedReader reader;

        public ProcessReader(InputStream inputStream) {
            this.reader = new BufferedReader(new InputStreamReader(inputStream));
        }

        @Override
        public List<String> call() throws IOException {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        }
    }
}
