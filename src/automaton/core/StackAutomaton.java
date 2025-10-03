package src.automaton.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import src.automaton.components.Alphabet;
import src.automaton.components.AutomatonSymbol;
import src.automaton.components.StackUtil;
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
  private final Map<TransitionKey, List<TransitionValue>> transitionMap;

  public StackAutomaton(StateSet stateSet, Alphabet inputAlphabet, Alphabet stackAlphabet, State initialState, AutomatonSymbol initialStackSymbol, Map<TransitionKey, List<TransitionValue>> transitionMap) {
    this.stateSet = stateSet;
    this.inputAlphabet = inputAlphabet;
    this.stackAlphabet = stackAlphabet;
    this.initialState = initialState;
    this.initialStackSymbol = initialStackSymbol;
    this.transitionMap = transitionMap;
  }

  public boolean run(String inputString) {
    StackUtil<AutomatonSymbol> initialStack = new StackUtil<AutomatonSymbol>();
    initialStack.push(this.initialStackSymbol);
    String cleanInputString = inputString.replace(".", "");
    return dfsExecution(this.initialState, cleanInputString, initialStack);
  }

  private boolean dfsExecution(State currentState, String currentInputString, StackUtil<AutomatonSymbol> currentStack) {
    if (currentStack.empty() && !currentInputString.isEmpty()) return false;
    if (currentStack.empty() && currentInputString.isEmpty()) return true;
    List<TransitionValue> possibleEmptyTransitions = transitionMap.getOrDefault(new TransitionKey(currentState, new AutomatonSymbol('.'), currentStack.peek()), new ArrayList<>());
    if (!currentStack.empty() && currentInputString.isEmpty() && possibleEmptyTransitions.isEmpty()) return false;
    if (!currentInputString.isEmpty()) {
      List<TransitionValue> possibleTransitions = transitionMap.getOrDefault(new TransitionKey(currentState, new AutomatonSymbol(currentInputString.charAt(0)), currentStack.peek()), new ArrayList<>());
      for (TransitionValue transitionValue : possibleTransitions) {
        StackUtil<AutomatonSymbol> newStack = currentStack.copy();
        newStack.pop();
        newStack.addAll(transitionValue.getStackSymbols());
        if (dfsExecution(transitionValue.getState(), currentInputString.substring(1), newStack)) return true;
      }
    }
    for (TransitionValue transitionValue : possibleEmptyTransitions) {
      StackUtil<AutomatonSymbol> newStack = currentStack.copy();
      newStack.pop();
      newStack.addAll(transitionValue.getStackSymbols());
      if(dfsExecution(transitionValue.getState(), currentInputString, newStack)) return true;
    }
    return false;
  }
}