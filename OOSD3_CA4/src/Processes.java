import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Processes {

    public static void main(String[] args) {

        String website = "jacksheehy.live"; // Define "jacksheehy.live" as the website to ping

        int numberOfPings = 10; // Define the number of times to ping the website

        ProcessBuilder processBuilder = new ProcessBuilder(); // Create a new process builder

        // Define the command to be run in the process builder
        // cmd.exe is command to execute on Windows
        // /c is the flag to run the command and then terminate
        // ping is the command to ping a remote host / website
        // -n is the flag to specify the number of times to ping the website
        // String.valueOf(numberOfPings) is converted to a string to fulfill the -n flag
        // website is the website to ping
        processBuilder.command("cmd.exe", "/c", "ping", "-n", String.valueOf(numberOfPings), website);

        try {
            // Start the process
            Process process = processBuilder.start();

            // Read the output of the process with BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            // Define a string to contain the output of the process
            String line;

            // Prints line to console until the value of line is null using readline()
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            process.waitFor();
        } catch (IOException | InterruptedException e) { // Catch any IOException or InterruptedException
            e.printStackTrace(); // Print the stack trace of the exception
        }
    }
}
// Output:
// Pinging website = "jacksheehy.live"
// 10 Replies from [76.76.21.21] as site is hosted with Vercel
// 32 Bytes of data per reply
// Times and TTLs of the pings
// Packets sent = 10, Received = 10, Lost = 0 (0% loss)
