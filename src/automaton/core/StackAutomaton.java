package src.automaton.core;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import src.automaton.components.Alphabet;
import src.automaton.components.AutomatonSymbol;
import src.automaton.components.StateSet;
import src.automaton.components.State;
import src.automaton.components.TransitionKey;
import src.automaton.components.TransitionValue;

public class StackAutomaton {

  private final StateSet stateSet;
  private final Alphabet inputAlphabet;
  private final Alphabet stackAlphabet;
  private final State initialState;
  private final AutomatonSymbol initialStackSymbol;
  private final Map<TransitionKey, List<TransitionValue>> transitionsMap;

  public StackAutomaton(StateSet stateSet, Alphabet inputAlphabet, Alphabet stackAlphabet, State initialState, AutomatonSymbol initialStackSymbol, Map<TransitionKey, List<TransitionValue>> transitionsMap) {
    this.stateSet = stateSet;
    this.inputAlphabet = inputAlphabet;
    this.stackAlphabet = stackAlphabet;
    this.initialState = initialState;
    this.initialStackSymbol = initialStackSymbol;
    this.transitionsMap = transitionsMap;
  }

  public boolean run(String inputString) {
    Stack<AutomatonSymbol> initialStack = new Stack<AutomatonSymbol>();
    initialStack.push(this.initialStackSymbol);
    return dfsExecution(this.initialState, inputString, initialStack);
  }

  private boolean dfsExecution(State currentState, String currentInputString, Stack<AutomatonSymbol> currentStack) {
    return false;
  }
}