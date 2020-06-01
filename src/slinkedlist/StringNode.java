package slinkedlist;

public class StringNode {

  // Instance variables:
  private String newton;
  private StringNode next;

  /**
   * Creates a node with null references to its element and next node.
   */
  public StringNode() {
    this("", null); //Newton Raphson, Next
  }

  /**
   * Creates a node with the given element and next node.
   *
   * @param e
   * @param p
   * @param n
   */
  public StringNode(String e, StringNode n) {
    newton = e;
    next = n;
  }

  // Accessor methods:
  public String getNewton() {
    return newton;
  }

  public StringNode getNext() {
    return next;
  }

  // Modifier methods:
  public void setNewton(String newNewton) {
    newton = newNewton;
  }

  public void setNext(StringNode nextNewton) {
    next = nextNewton;
  }
}
