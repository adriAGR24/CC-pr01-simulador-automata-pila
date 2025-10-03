package src.automaton;

import src.automaton.io.ReadAutomaton;

public class Main {
  public static void main(String[] args) {
    try { 
      ReadAutomaton.readFileAutomaton(args[0]);
    } catch (Exception error) {
      System.err.println("ERROR: " + error.getMessage());
      System.exit(1);
    }
  }
}
