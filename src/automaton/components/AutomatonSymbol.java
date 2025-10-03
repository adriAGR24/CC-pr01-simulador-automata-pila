package src.automaton.components;

public class AutomatonSymbol {

  private final char id;

  public AutomatonSymbol(String symbolId) {
    if (symbolId.split("\\s+").length > 1) {
      throw new IllegalArgumentException("only one symbol can be the initial stack symbol: " + symbolId + " declared");
    }
    if (symbolId.length() > 1) {
      throw new IllegalArgumentException("symbol should be only one character: " + symbolId + " is not a single character");
    }
    this.id = symbolId.charAt(0);
  }

  public AutomatonSymbol(char symbolId) {
    this.id = symbolId;
  }

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
