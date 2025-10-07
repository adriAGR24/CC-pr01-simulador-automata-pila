package src.automaton.components;

import java.util.Stack;

/**
 * Extension of {@link Stack} with additional utilities used by the simulator.
 *
 * <p>Provides a {@code copy} method to clone the stack (shallow copy) and a
 * {@code toString} that returns the representation of the stack from top to
 * bottom, using '.' to denote an empty stack.</p>
 */
public class StackUtil<E> extends Stack<E> {
  /**
   * Creates a shallow copy of the stack.
   *
   * @return new instance of {@code StackUtil} containing the same elements in the same order.
   */
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
