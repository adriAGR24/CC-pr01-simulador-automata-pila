package src.automaton.utils;


public class ArgumentsParser {
  
  public String automatonFileRoute;
  public String stringsFileRoute;
  public boolean trace = false;

  private final String useText =
    "How to use: ./runProgram.sh -a <automaton_file> -s <strings_file> [-t]\n"
  + "Try ./runProgram.sh -h for more information.";

  private final String helpText =
    "This program is a Stack Automaton simulator, designed to check if a series of string are accepted by a specific automaton.\n\n"
  + "Usage: ./runProgram.sh -a <automaton_file> -s <strings_file> [-t]\n\n"
  + "Arguments:\n"
  + "- -a <automaton_file>: The file containing the stack automaton definition.\n"
  + "- -s <strings_file>: The file containing the strings to check.\n"
  + "- [-t]: Trace mode. If not specified: false.";

  /**
   * Parses command line arguments and populates public fields with the
   * corresponding values. Exits with help message if -h is provided.
   *
   * @param args program arguments
   */
  public ArgumentsParser(String[] args) {
    for (int i = 0; i < args.length; ++i) {
      String argument = args[i];
      if (argument.equals("-h")) {
        System.out.println(helpText);
        System.exit(0);
      }
      else if ((argument.equals("-a")) && (i + 1 < args.length)) {
        automatonFileRoute = args[++i];
      }
      else if ((argument.equals("-s")) && (i + 1 < args.length)) {
        stringsFileRoute = args[++i];
      }
      else if ((argument.equals("-t"))) {
        trace = true;
      }
    }

    if (automatonFileRoute == null) {
      System.out.println(useText);
      throw new IllegalArgumentException("no automaton file provided");
    }
    if (stringsFileRoute == null) {
      System.out.println(useText);
      throw new IllegalArgumentException("no strings file provided");
    }
  }

}
