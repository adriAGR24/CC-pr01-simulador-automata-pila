package src.automaton.simulation;

import java.io.IOException;
import java.util.List;

import src.automaton.core.StackAutomaton;

/**
 * Simulator wrapper that runs a {@link StackAutomaton} over a list of input
 * strings and optionally prints a detailed execution trace.
 *
 * <p>This class is responsible for formatting and printing the automaton
 * definition and the results of checking multiple strings.</p>
 */
public class StackAutomatonSimulator {

  /** The automaton to simulate. */
  private final StackAutomaton automaton;

  /** List of raw strings to check (may contain '.' as placeholder for empty symbol). */
  private final List<String> stringsToCheck;

  /**
   * Creates a simulator for the provided automaton and strings.
   *
   * @param stackAutomaton the automaton instance to run
   * @param stringsToCheck list of strings to validate against the automaton
   * @throws IOException kept for compatibility with callers that expect IO-related errors
   */
  public StackAutomatonSimulator(StackAutomaton stackAutomaton, List<String> stringsToCheck) throws IOException {
    this.automaton = stackAutomaton;
    this.stringsToCheck = stringsToCheck;
  }

  /**
   * Runs the simulator and prints the automaton definition and results to
   * standard output. If {@code trace} is true, also prints the step-by-step
   * execution trace for each string.
   *
   * @param trace whether to include execution traces in the output
   */
  public void run(boolean trace) {
    System.out.println("\u001b[1m\u001b[4mStack Automaton Definition\u001b[0m");
    System.out.println();
    System.out.println(automaton.toString() + "\n\n");
    System.out.println("\u001b[1m\u001b[4mString Checking\u001b[0m");
    System.out.println();
    System.out.println(checkStrings(trace));
  }

  /**
   * Checks all configured strings against the automaton and returns a
   * formatted result. When {@code trace} is enabled, the automaton's trace
   * (last execution) is appended after each string's acceptance result.
   *
   * @param trace whether to include execution traces
   * @return formatted results for all strings
   */
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
