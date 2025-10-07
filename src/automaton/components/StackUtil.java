package src.automaton.components;

import java.util.Stack;

public class StackUtil<E> extends Stack<E> {
  public StackUtil<E> copy() {
    StackUtil<E> copy = new StackUtil<E>();
    copy.addAll(this);
    return copy;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = this.size() - 1; i >= 0; --i) {
      sb.append(this.get(i).toString());
    }
    if (sb.length() == 0) sb.append(".");
    return sb.toString();
  }
}
