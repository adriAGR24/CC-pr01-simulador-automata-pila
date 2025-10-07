package src.automaton.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import src.automaton.components.Alphabet;
import src.automaton.components.AutomatonSymbol;
import src.automaton.components.StackUtil;
import src.automaton.components.StateSet;
import src.automaton.components.Transition;
import src.automaton.components.State;
import src.automaton.components.TransitionKey;
import src.automaton.components.TransitionValue;

/**
 * Implementation of a pushdown automaton and its depth-first search (DFS)
 * execution engine.
 *
 * <p>It is configured with state sets and alphabets, an initial state,
 * an initial stack symbol and a transition map. It provides methods to run
 * the automaton on an input string and to retrieve the execution trace.</p>
 */
public class StackAutomaton {

  private final StateSet stateSet;
  private final Alphabet inputAlphabet;
  private final Alphabet stackAlphabet;
  private final State initialState;
  private final AutomatonSymbol initialStackSymbol;
  private final Map<TransitionKey, List<TransitionValue>> transitionMap;

  private ArrayList<String> trace = new ArrayList<String>();
  private final String TRACE_FORMAT = "%-20s %-20s %-20s %-20s";
  private final int MAX_LIMIT = 1000;

  /**
   * Constructs the automaton with its full definition.
   *
   * @param stateSet set of states (Q)
   * @param inputAlphabet input alphabet (Σ)
   * @param stackAlphabet stack alphabet (Γ)
   * @param initialState initial state (s)
   * @param initialStackSymbol initial stack symbol (Z)
   * @param transitionMap transition map δ
   */
  public StackAutomaton(StateSet stateSet, Alphabet inputAlphabet, Alphabet stackAlphabet, State initialState, AutomatonSymbol initialStackSymbol, Map<TransitionKey, List<TransitionValue>> transitionMap) {
    this.stateSet = stateSet;
    this.inputAlphabet = inputAlphabet;
    this.stackAlphabet = stackAlphabet;
    this.initialState = initialState;
    this.initialStackSymbol = initialStackSymbol;
    this.transitionMap = transitionMap;
  }

  /**
   * Executes the automaton on the given input string.
   *
   * @param inputString input string (no separators)
   * @return {@code true} if the string is accepted, {@code false} otherwise
   */
  public boolean run(String inputString) {
    trace.clear();
    StackUtil<AutomatonSymbol> initialStack = new StackUtil<AutomatonSymbol>();
    initialStack.push(this.initialStackSymbol);
    trace.add(String.format(TRACE_FORMAT, "Current State", "Input String", "Stack", "Possible Transitions"));
    return dfsExecution(this.initialState, inputString, initialStack, 0);
  }

  /**
   * Retrieves the textual trace of the last execution.
   *
   * @return multi-line representation of the trace
   */
  public String getTrace() {
    StringBuilder sb = new StringBuilder();
    for (String row : trace)
      sb.append(row).append('\n');;
    return sb.toString();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Q = ").append(stateSet).append('\n');
    sb.append("Σ = ").append(inputAlphabet).append('\n');
    sb.append("Γ = ").append(stackAlphabet).append('\n');
    sb.append("s = ").append(initialState.getStateId()).append('\n');
    sb.append("Z = ").append(initialStackSymbol.getSymbolId()).append('\n');
    sb.append("δ:").append('\n');
    int totalTransitions = transitionMap.values().stream()
      .mapToInt(List::size)
      .sum();
    int transitionCounter = 0;
    for (Map.Entry<TransitionKey, List<TransitionValue>> entry : transitionMap.entrySet()) {
      for (TransitionValue transitionValue : entry.getValue()) {
        sb.append(new Transition(entry.getKey(), transitionValue));
        if (transitionCounter < totalTransitions - 1) sb.append('\n');
        ++transitionCounter;
      } 
    }
    return sb.toString();
  }

  /**
   * DFS implementation that explores possible automaton executions.
   *
   * <p>The {@code limit} parameter is used to detect possible infinite
   * loops; if {@code MAX_LIMIT} is reached an exception is thrown.</p>
   *
   * @param currentState current state
   * @param currentInputString remaining input suffix
   * @param currentStack current stack
   * @param limit counter used for loop detection
   * @return {@code true} if some branch leads to acceptance
   */
  private boolean dfsExecution(State currentState, String currentInputString, StackUtil<AutomatonSymbol> currentStack, int limit) {
    if (limit >= MAX_LIMIT) throw new IllegalStateException("possible loop detected in automaton traversal");
    if (currentStack.empty() && !currentInputString.isEmpty()) {
      trace.add(String.format(TRACE_FORMAT, currentState.toString(), currentInputString, currentStack.toString(), "∄"));
      return false;
    }
    if (currentStack.empty() && currentInputString.isEmpty()) {
      trace.add(String.format(TRACE_FORMAT, currentState.toString(), currentInputString, currentStack.toString(), "∄"));
      return true;
    }

    List<TransitionValue> possibleEmptyTransitions = transitionMap.getOrDefault(new TransitionKey(currentState, new AutomatonSymbol('.'), currentStack.peek()), new ArrayList<>());
    if (!currentStack.empty() && currentInputString.isEmpty() && possibleEmptyTransitions.isEmpty()) {
      trace.add(String.format(TRACE_FORMAT, currentState.toString(), currentInputString, currentStack.toString(), "∄"));
      return false;
    }

    String transitionsIds;
    if (!currentInputString.isEmpty()) {
      List<TransitionValue> possibleTransitions = transitionMap.getOrDefault(new TransitionKey(currentState, new AutomatonSymbol(currentInputString.charAt(0)), currentStack.peek()), new ArrayList<>());
      List<TransitionValue> transitions = new ArrayList<TransitionValue>(possibleTransitions);
      transitions.addAll(possibleEmptyTransitions);
      transitionsIds = getTransitionsIds(transitions);
      trace.add(String.format(TRACE_FORMAT, currentState.toString(), currentInputString, currentStack.toString(), transitionsIds));
      for (TransitionValue transitionValue : possibleTransitions) {
        StackUtil<AutomatonSymbol> newStack = currentStack.copy();
        newStack.pop();
        newStack.addAll(transitionValue.getStackSymbols());
        if (dfsExecution(transitionValue.getState(), currentInputString.substring(1), newStack, limit + 1)) return true;
        trace.add("─".repeat(80));
        trace.add(String.format(TRACE_FORMAT, currentState.toString(), currentInputString, currentStack.toString(), transitionsIds));
        limit = 0;
      }
    } else {
      transitionsIds = getTransitionsIds(possibleEmptyTransitions);
      trace.add(String.format(TRACE_FORMAT, currentState.toString(), currentInputString, currentStack.toString(), transitionsIds));
    }
    for (TransitionValue transitionValue : possibleEmptyTransitions) {
      StackUtil<AutomatonSymbol> newStack = currentStack.copy();
      newStack.pop();
      newStack.addAll(transitionValue.getStackSymbols());
      if(dfsExecution(transitionValue.getState(), currentInputString, newStack, limit + 1)) return true;
      trace.add("─".repeat(80));
      trace.add(String.format(TRACE_FORMAT, currentState.toString(), currentInputString, currentStack.toString(), transitionsIds));
      limit = 0;
    }
    return false;
  }

  private String getTransitionsIds(List<TransitionValue> transitions) {
    String transitionsIds = transitions.stream()
      .map(t -> String.valueOf(t.getId()))
      .collect(Collectors.joining(", "));
    return transitionsIds.equals("") ? "∄" : transitionsIds;
  }
}