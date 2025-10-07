package src.automaton.simulation;

import java.io.IOException;
import java.util.List;

import src.automaton.core.StackAutomaton;

public class StackAutomatonSimulator {

  private final StackAutomaton automaton;
  private final List<String> stringsToCheck;

  public StackAutomatonSimulator(StackAutomaton stackAutomaton, List<String> stringsToCheck) throws IOException {
    this.automaton = stackAutomaton;
    this.stringsToCheck = stringsToCheck;
  }

  public void run(boolean trace) {
    System.out.println("\u001b[1m\u001b[4mStack Automaton Definition\u001b[0m");
    System.out.println();
    System.out.println(automaton.toString() + "\n\n");
    System.out.println("\u001b[1m\u001b[4mString Checking\u001b[0m");
    System.out.println();
    System.out.println(checkStrings(trace));
  }

  public String checkStrings(boolean trace) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < stringsToCheck.size(); ++i) {
      String cleanInputString = stringsToCheck.get(i).replace(".", "");
      boolean accepted = automaton.run(cleanInputString);
      sb.append("String '" + cleanInputString + "' accepted: " + accepted);
      if (trace || i < stringsToCheck.size() - 1) sb.append("\n\n");
      if (trace) {
        sb.append(automaton.getTrace());
        if (i < stringsToCheck.size() - 1) sb.append("\n\n");
      }
    }
    return sb.toString();
  }

}
