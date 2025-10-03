package src.automaton.components;

public class Transition {

  private final TransitionKey key;
  private final TransitionValue value;

  public Transition(TransitionKey key, TransitionValue value) {
    this.key = key;
    this.value = value;
  }

  public TransitionKey getKey() {
    return key;
  }

  public TransitionValue getValue() {
    return value;
  }

  @Override
  public String toString() {
    return this.key.toString() + " = " + this.value.toString();
  }

}
