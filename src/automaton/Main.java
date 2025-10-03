package src.automaton;

import src.automaton.core.StackAutomaton;
import src.automaton.io.ReadAutomaton;

public class Main {
  public static void main(String[] args) {
    try { 
      StackAutomaton automaton = ReadAutomaton.readFileAutomaton(args[0]);
      String string = "....";
      System.out.println("String '" + string + "': " + automaton.run(string));
    } catch (Exception error) {
      System.err.println("ERROR: " + error.getMessage());
      System.exit(1);
    }
  }
}
