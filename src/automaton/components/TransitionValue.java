package src.automaton.components;

public class TransitionValue {

  private final State state;
  private final StackUtil<AutomatonSymbol> stackSymbols;
  private final int id;

  public TransitionValue(State state, String stackSymbols, int id) {
    this.state = state;
    this.id = id;

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

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "(" + state + ", " + stackSymbols.toString() + ")";
  }

}