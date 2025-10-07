package src.automaton.components;

/**
 * Represents an atomic symbol of the automaton (a single character).
 *
 * <p>This class encapsulates a single character that can be used both as an
 * input symbol and as a stack symbol. It implements {@code equals} and
 * {@code hashCode} to allow usage in collections.</p>
 */
public class AutomatonSymbol {

  private final char id;

  /**
   * Constructs a symbol from a string that must contain exactly one character.
   *
   * @param symbolId string with a single character representing the symbol
   * @throws IllegalArgumentException if the string is empty or contains more
   *         than one token/character
   */
  public AutomatonSymbol(String symbolId) {
    if (symbolId.split("\\s+").length > 1) {
      throw new IllegalArgumentException("only one symbol can be the initial stack symbol: " + symbolId + " declared");
    }
    if (symbolId.length() > 1) {
      throw new IllegalArgumentException("symbol should be only one character: " + symbolId + " is not a single character");
    }
    this.id = symbolId.charAt(0);
  }

  /**
   * Constructs a symbol from a character.
   *
   * @param symbolId character that identifies the symbol
   */
  public AutomatonSymbol(char symbolId) {
    this.id = symbolId;
  }

  /**
   * Returns the symbol identifier as a string.
   *
   * @return string containing the character that represents the symbol
   */
  public String getSymbolId() {
    return id + "";
  }

  @Override
  public String toString() {
    return this.id + "";
  }

  @Override
  public boolean equals(Object obj) {
      if (this == obj) return true;
      if (!(obj instanceof AutomatonSymbol)) return false;
      AutomatonSymbol other = (AutomatonSymbol) obj;
      return this.id == other.id;
  }

  @Override
  public int hashCode() {
      return Character.hashCode(id);
  }

}
