package src.automaton.components;

import java.util.Objects;

/**
 * Represents an automaton state identified by a string.
 *
 * <p>The class is immutable: the identifier is set in the constructor and
 * cannot be modified afterwards. It implements {@code equals} and
 * {@code hashCode} for collection usage.</p>
 */
public class State {

  /** Identificador del estado. */
  private final String id;

  /**
   * Constructs a state with a single-word identifier (no spaces).
   *
   * @param stateId state identifier
   * @throws IllegalArgumentException if {@code stateId} contains spaces
   */
  public State(String stateId) throws IllegalArgumentException {
    if (stateId.split("\\s+").length > 1) {
      throw new IllegalArgumentException("only one state can be the initial state: " + stateId + " declared");
    }
    this.id = stateId;
  }

  /**
   * Returns the state identifier.
   *
   * @return the state identifier
   */
  public String getStateId() {
    return this.id;
  }

  @Override
  public String toString() {
    return this.id + "";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    State other = (State) obj;
    return Objects.equals(this.id, other.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

}
