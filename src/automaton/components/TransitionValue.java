package src.automaton.components;

import java.util.Stack;

public class TransitionValue {

  private final State state;
  private final Stack<AutomatonSymbol> stackSymbols;

  public TransitionValue(State state, String stackSymbols) {
    this.state = state;

    Stack<AutomatonSymbol> stack = new Stack<AutomatonSymbol>();
    for (int i = stackSymbols.length() - 1; i >= 0; --i) {
      if (stackSymbols.charAt(i) == '.') continue;
      stack.push(new AutomatonSymbol(stackSymbols.charAt(i)));
    }
    this.stackSymbols = stack;
  }

  public State getState() {
    return this.state;
  }

  public Stack<AutomatonSymbol> getStackSymbols() {
    return this.stackSymbols;
  }

  @Override
  public String toString() {
    String stackSymbolString = "";
    for (int i = this.stackSymbols.size() - 1; i >= 0; --i) {
      stackSymbolString += this.stackSymbols.get(i).getSymbolId();
    }
    return "(" + state + ", " + stackSymbolString + ")";
  }

}