package src.automaton.components;

import java.util.ArrayList;
import java.util.HashSet;

public class Alphabet extends HashSet<AutomatonSymbol> {

  public Alphabet(String[] symbolArray) throws IllegalArgumentException {
    ArrayList<AutomatonSymbol> symbolList = new ArrayList<AutomatonSymbol>();
    for (String symbol : symbolArray) {
      try {
        symbolList.add(new AutomatonSymbol(symbol));
      } catch (IllegalArgumentException error) {
        throw new IllegalArgumentException("alphabet should only contain symbols: " + symbol + " is not a single character");
      }
    }
    super.addAll(symbolList);
  }

  public Alphabet() {}

}