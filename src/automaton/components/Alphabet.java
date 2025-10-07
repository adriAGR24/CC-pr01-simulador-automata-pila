package src.automaton.components;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents an automaton alphabet as a set of symbols.
 *
 * <p>Extends {@link HashSet} parameterized with {@link AutomatonSymbol} to
 * store the valid symbols for the alphabet (either input or stack alphabet).
 *
 * <p>Notes:
 * - The constructor that accepts an array validates that each element is a
 *   single-character symbol and will throw {@link IllegalArgumentException}
 *   otherwise.
 */
public class Alphabet extends HashSet<AutomatonSymbol> {

  /**
   * Constructs an alphabet from an array of strings where each string
   * represents a single-character symbol.
   *
   * @param symbolArray array of strings with the symbols
   * @throws IllegalArgumentException if any string does not represent a
   *         single-character symbol
   */
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

  /**
   * Constructs an empty alphabet.
   */
  public Alphabet() {}

}