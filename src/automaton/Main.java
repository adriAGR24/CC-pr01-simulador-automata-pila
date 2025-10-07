package src.automaton;

import java.util.List;

import src.automaton.core.StackAutomaton;
import src.automaton.io.ReadStackAutomaton;
import src.automaton.io.ReadStringFile;
import src.automaton.simulation.StackAutomatonSimulator;
import src.automaton.utils.ArgumentsParser;

public class Main {
  public static void main(String[] args) {
    try {
      ArgumentsParser parser = new ArgumentsParser(args);
      StackAutomaton stackAutomaton = ReadStackAutomaton.readFileAutomaton(parser.automatonFileRoute);
      List<String> stringsToCheck = ReadStringFile.readPerSpace(parser.stringsFileRoute);
      StackAutomatonSimulator simulator = new StackAutomatonSimulator(stackAutomaton, stringsToCheck);
      simulator.run(parser.trace);
    } catch (Exception error) {
      System.err.println("ERROR: " + error.getMessage());
      System.exit(1);
    }
  }
}