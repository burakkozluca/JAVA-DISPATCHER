import java.io.*;
import java.util.*;

public class Shell {
  public static void main(String[] args) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (true) {

      System.out.print("> ");

      String line = reader.readLine();

      List<String> tokens = new ArrayList<>();
      for (String token : line.split(" "))
      {
        tokens.add(token);
      }
      String[] cmd = tokens.toArray(new String[0]);

      if (cmd[0].equals("exit"))
      {
        break;
      }

      ProcessBuilder pb = new ProcessBuilder(cmd);
      Process process = pb.start();

      BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String Line;
      while ((line = output.readLine()) != null)
      {
        System.out.println(line);
      }
    }
  }
}
