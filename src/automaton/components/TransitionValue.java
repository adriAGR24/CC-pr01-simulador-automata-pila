package src.automaton.components;

public class TransitionValue {

  private final State state;
  private final StackUtil<AutomatonSymbol> stackSymbols;

  public TransitionValue(State state, String stackSymbols) {
    this.state = state;

    StackUtil<AutomatonSymbol> stack = new StackUtil<AutomatonSymbol>();
    for (int i = stackSymbols.length() - 1; i >= 0; --i) {
      if (stackSymbols.charAt(i) == '.') continue;
      stack.push(new AutomatonSymbol(stackSymbols.charAt(i)));
    }
    this.stackSymbols = stack;
  }

  public State getState() {
    return this.state;
  }

  public StackUtil<AutomatonSymbol> getStackSymbols() {
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