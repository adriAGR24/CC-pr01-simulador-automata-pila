package src.automaton.components;

import java.util.Stack;

public class StackUtil<E> extends Stack<E> {
  public StackUtil<E> copy() {
    StackUtil<E> copy = new StackUtil<E>();
    copy.addAll(this);
    return copy;
  }
}
