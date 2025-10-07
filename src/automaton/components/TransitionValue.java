package src.automaton.components;

/**
 * Represents the result/action of a transition: destination state and the
 * sequence of symbols that must be placed on the stack.
 */
public class TransitionValue {

  private final State state;
  private final StackUtil<AutomatonSymbol> stackSymbols;
  private final int id;

  /**
   * Constructs a TransitionValue.
   *
   * <p>The {@code stackSymbols} parameter is interpreted as a string where
   * each character (except '.') represents a symbol to be pushed; the string
   * is processed from right to left to preserve the correct stack order.</p>
   *
   * @param state destination state of the transition
   * @param stackSymbols string with the symbols to push (use '.' for empty)
   * @param id numeric identifier of the transition (for traces)
   */
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

  /**
   * Returns the destination state.
   *
   * @return destination state
   */
  public State getState() {
    return this.state;
  }

  /**
   * Returns the symbols that should be pushed as a {@link StackUtil}.
   *
   * @return stack with the symbols to push (in top-to-bottom order)
   */
  public StackUtil<AutomatonSymbol> getStackSymbols() {
    return this.stackSymbols;
  }

  /**
   * Numeric identifier of the transition (useful for traces).
   *
   * @return transition id
   */
  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "(" + state + ", " + stackSymbols.toString() + ")";
  }

}