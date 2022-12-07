import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Shell {
  public static void main(String[] args) throws IOException {
    // Create a BufferedReader to read input from the user
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      // Display prompt
      System.out.print("> ");

      // Read input
      String line = reader.readLine();

      // Parse input to extract command and arguments
      List<String> tokens = new ArrayList<>();
      for (String token : line.split(" ")) {
        tokens.add(token);
      }
      String[] cmd = tokens.toArray(new String[0]);

      // Exit shell if command is "exit"
      if (cmd[0].equals("exit")) {
        break;
      }

      // Execute command
      ProcessBuilder pb = new ProcessBuilder(cmd);
      Process process = pb.start();

      // Print output of command
      BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = output.readLine()) != null) {
        System.out.println(line);
      }
    }
  }
}
