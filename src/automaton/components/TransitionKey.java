package src.automaton.components;

import java.util.Objects;

public class TransitionKey {

  private final State state;
  private final AutomatonSymbol inputSymbol;
  private final AutomatonSymbol stackSymbol;

  public TransitionKey(State state, AutomatonSymbol inputSymbol, AutomatonSymbol stackSymbol) {
    this.state = state;
    this.inputSymbol = inputSymbol;
    this.stackSymbol = stackSymbol;
  }

  public State getState() {
    return state;
  }

  public AutomatonSymbol getInputSymbol() {
    return inputSymbol;
  }

  public AutomatonSymbol getStackSymbol() {
    return stackSymbol;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    TransitionKey other = (TransitionKey) obj;
    return Objects.equals(state, other.state) &&
           Objects.equals(inputSymbol, other.inputSymbol) &&
           Objects.equals(stackSymbol, other.stackSymbol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(state, inputSymbol, stackSymbol);
  }

  @Override
  public String toString() {
    return "(" + state + ", " + inputSymbol + ", " + stackSymbol + ")";
  }

}