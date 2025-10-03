package src.automaton.components;

import java.util.Objects;

public class State {

  private final String id;

  public State(String stateId) throws IllegalArgumentException {
    if (stateId.split("\\s+").length > 1) {
      throw new IllegalArgumentException("only one state can be the initial state: " + stateId + " declared");
    }
    this.id = stateId;
  }

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
