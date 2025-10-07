package src.automaton.components;

/**
 * Represents an automaton transition composed of a key (condition)
 * and a value (action/result).
 */
public class Transition {

  private final TransitionKey key;
  private final TransitionValue value;

  /**
   * Constructs a transition from its key and value.
   *
   * @param key  transition key (state, input symbol, stack symbol)
   * @param value transition value (destination state and symbols to push)
   */
  public Transition(TransitionKey key, TransitionValue value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Returns the key of the transition.
   *
   * @return transition key
   */
  public TransitionKey getKey() {
    return key;
  }

  /**
   * Returns the value/result of the transition.
   *
   * @return transition value
   */
  public TransitionValue getValue() {
    return value;
  }

  @Override
  public String toString() {
    return this.key.toString() + " = " + this.value.toString();
  }

}
