package src.automaton.components;

import java.util.*;

import java.util.HashSet;

/**
 * Set of automaton states.
 *
 * <p>Extends {@link HashSet} parameterized with {@link State} and provides a
 * convenience constructor that accepts an array of state identifiers.</p>
 */
public class StateSet extends HashSet<State> {

  /**
   * Constructs a StateSet from an array of state identifiers.
   *
   * @param stateArray array with the state identifiers
   */
  public StateSet(String[] stateArray) {
    ArrayList<State> stateList = new ArrayList<State>();
    for (String state : stateArray) {
      stateList.add(new State(state));
    }
    super.addAll(stateList);
  }

  /**
   * Constructs an empty StateSet.
   */
  public StateSet() {}
  
}
