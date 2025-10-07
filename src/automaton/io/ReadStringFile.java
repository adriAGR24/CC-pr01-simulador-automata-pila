package src.automaton.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ReadStringFile {

  /**
   * Reads the file and splits its content by whitespace into a list of strings.
   *
   * @param fileRoute path to the file with strings
   * @return list of strings read from the file (split by whitespace)
   * @throws IOException if the file cannot be read
   */
  public static List<String> readPerSpace(String fileRoute) throws IOException {
    String fileContent;
    try {
      fileContent = String.join("\n", Files.readAllLines(Paths.get(fileRoute)));
    } catch (IOException error) {
      throw new IOException("couldn't read file " + fileRoute);
    }
    String[] splittedStrings = fileContent.split("\\s+");
    return Arrays.asList(splittedStrings);
  }
  
}