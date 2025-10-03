package src.automaton.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.automaton.components.*;
import src.automaton.core.StackAutomaton;

// TODO: completar verificaciones (epsilon no puede estar en alfabeto)

public class ReadAutomaton {

  public static StackAutomaton readFileAutomaton(String fileRoute) throws IOException {
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(fileRoute));
    } catch (IOException error) {
      throw new IOException("couldn't read file " + fileRoute);
    }

    String line;
    try {
      while ((line = br.readLine()).startsWith("#"));
    } catch (NullPointerException error) {
      br.close();
      throw new IllegalArgumentException("no set of states declared in input file");
    }
    if (line.trim().length() == 0) {
      br.close();
      throw new IllegalArgumentException("no set of states declared in input file");
    }
    StateSet stateSet = new StateSet(line.trim().split("\\s+"));
    System.out.println(stateSet);

    line = checkMandatoryLine("no input alphabet declared in input file", br);
    Alphabet inputAlphabet = new Alphabet(line.trim().split("\\s+"));
    System.out.println(inputAlphabet);

    line = checkMandatoryLine("no stack alphabet declared in input file", br);
    Alphabet stackAlphabet = new Alphabet(line.trim().split("\\s+"));
    System.out.println(stackAlphabet);

    line = checkMandatoryLine("no initial state declared in input file", br);
    State initialState = new State(line.trim());
    System.out.println(initialState.getStateId());

    line = checkMandatoryLine("no initial stack symbol declared in input file", br);
    AutomatonSymbol initialStackSymbol = new AutomatonSymbol(line.trim());
    System.out.println(initialStackSymbol.getSymbolId());

    ArrayList<Transition> transitions = new ArrayList<Transition>();
    while ((line = br.readLine()) != null) {
      line = line.trim();
      if (line.length() == 0){
        br.close();
        throw new IllegalArgumentException("empty transition declared in input file");
      }
      String[] transitionArray = line.split("\\s+");
      if (transitionArray.length != 5) {
        br.close();
        throw new IllegalArgumentException("transition with invalid number of elements declared in input file");
      }
      TransitionKey key = new TransitionKey(new State(transitionArray[0]), new AutomatonSymbol(transitionArray[1]), new AutomatonSymbol(transitionArray[2]));
      TransitionValue value = new TransitionValue(new State(transitionArray[3]), transitionArray[4]);
      System.out.println(new Transition(key, value));
      transitions.add(new Transition(key, value));
    }
    br.close();
    return checkValidAutomaton(stateSet, inputAlphabet, stackAlphabet, initialState, initialStackSymbol, transitions);
  }

  private static String checkMandatoryLine(String errorMessage, BufferedReader br) throws IllegalArgumentException, IOException {
    String line;
    if ((line = br.readLine()) == null || line.trim().length() == 0) {
      br.close();
      throw new IllegalArgumentException(errorMessage);
    }
    return line;
  }

  private static StackAutomaton checkValidAutomaton(StateSet stateSet, Alphabet inputAlphabet, Alphabet stackAlphabet, State initialState, AutomatonSymbol initialStackSymbol, ArrayList<Transition> transitions) {
    if (!stateSet.contains(initialState)) {
      throw new IllegalArgumentException("initial state do not belongs to state set");
    }
    if (!stackAlphabet.contains(initialStackSymbol)) {
      throw new IllegalArgumentException("initial stack symbol do not belongs to stack alphabet");
    }
    Map<TransitionKey, List<TransitionValue>> transitionMap = new HashMap<>();
    for (Transition transition : transitions) {
      checkValidTransition(stateSet, inputAlphabet, stackAlphabet, transition);
      TransitionKey key = transition.getKey();
      TransitionValue value = transition.getValue();
      transitionMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }
    return new StackAutomaton(stateSet, inputAlphabet, stackAlphabet, initialState, initialStackSymbol, transitionMap);
  }

  private static void checkValidTransition(StateSet stateSet, Alphabet inputAlphabet, Alphabet stackAlphabet, Transition transition) {
    TransitionKey key = transition.getKey();
    TransitionValue value = transition.getValue();
    if (!stateSet.contains(key.getState())) {
      throw new IllegalArgumentException("state in transition do not belongs to state set");
    }
    if (!inputAlphabet.contains(key.getInputSymbol()) && !key.getInputSymbol().getSymbolId().equals(".")) {
      throw new IllegalArgumentException("input symbol in transition do not belongs to input alphabet or empty string");
    }
    if (!stackAlphabet.contains(key.getStackSymbol())) {
      throw new IllegalArgumentException("stack symbol in transition do not belongs to stack alphabet");
    }
    if (!stateSet.contains(value.getState())) {
      throw new IllegalArgumentException("state in transition do not belongs to state set");
    }
    for (AutomatonSymbol symbol : value.getStackSymbols()) {
      if (!stackAlphabet.contains(symbol)) {
        throw new IllegalArgumentException("stack symbol in transition do not belongs to stack alphabet or empty string");
      }
    }
  }

}
