package src.automaton.components;

import java.util.*;

import java.util.HashSet;

public class StateSet extends HashSet<State> {

  public StateSet(String[] stateArray) {
    ArrayList<State> stateList = new ArrayList<State>();
    for (String state : stateArray) {
      stateList.add(new State(state));
    }
    super.addAll(stateList);
  }

  public StateSet() {}
  
}
