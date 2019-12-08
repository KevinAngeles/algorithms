/**
 * Java Package to create and manipulate Binary Search Trees
 * 
 * This was adapted from the python version of bst.py BY Erik Demaine, and Srini Devadas. 
 * Massachusetts Institute of Technology: MIT OpenCourseWare
 * 6.006 Introduction to Algorithms. Fall 2011.
 * Lecture: AVL
 * https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/lecture-videos/lec06_code.zip
 * 
 * License: Creative Commons BY-NC-SA.
 */
package datastructures.trees.bst.nodes;

public class BSTNode {
  private Integer key;
  private BSTNode parent;
  private BSTNode left;
  private BSTNode right;
  private Integer height;

  public BSTNode(BSTNode parent, Integer k) {
    this.setKey(k);
    this.setParent(parent);
    this.setLeft(null);
    this.setRight(null);
    // height is only used for AVLs
    this.setHeight(0);
  }

  /**
   * Finds the node with key k from the subtree rooted at this node.
   *
   * @param k The key of the node we want to be found.
   * @return The node with key k if found or null if not found.
   */
  public BSTNode find(Integer k) {
    if (k == null) {
      return null;
    }
    if (k.equals(this.getKey())) {
      return this;
    } else if (k.compareTo(this.getKey()) < 0) {
      if (this.getLeft() == null) {
        return null;
      } else {
        return this.getLeft().find(k);
      }
    } else {
      if (this.getRight() == null) {
        return null;
      } else {
        return this.getRight().find(k);
      }
    }
  }

  /**
   * Finds the node with the minimum key in the subtree rooted at this node.
   * 
   * @return The node with the minimum key.
   */
  public BSTNode findMin() {
    BSTNode current = this;
    while (current.getLeft() != null) {
      current = current.getLeft();
    }

    return current;
  }

  /**
   * Finds the node with the maximum key in the subtree rooted at this node.
   * 
   * @return The node with the maximum key.
   */
  public BSTNode findMax() {
    BSTNode current = this;
    while (current.getRight() != null) {
      current = current.getRight();
    }

    return current;
  }

  /**
   * Finds the node with the next smaller key.
   * 
   * @return The precessor node if it exists or null if there is no precessor
   *         node.
   */
  public BSTNode nextSmaller() {
    if (this.getLeft() != null) {
      return this.getLeft().findMax();
    }
    BSTNode current = this;
    while (current.getParent() != null && current == current.getParent().getLeft()) {
      current = current.getParent();
    }

    return current.getParent();
  }

  /**
   * Finds the node with the next larger key.
   * 
   * @return The successor node if it exists or null if there is no successor
   *         node.
   */
  public BSTNode nextLarger() {
    if (this.getRight() != null) {
      return this.getRight().findMin();
    }
    BSTNode current = this;
    while (current.getParent() != null && current == current.getParent().getRight()) {
      current = current.getParent();
    }

    return current.getParent();
  }

  /**
   * Inserts a node into the subtree rooted at this node.
   * 
   * @param node The node to be inserted.
   */
  public void insert(BSTNode node) {
    if (node == null || node.getKey() == null) {
      return;
    }
    if (node.getKey().compareTo(this.getKey()) < 0) {
      if (this.getLeft() == null) {
        node.setParent(this);
        this.setLeft(node);
      } else {
        this.getLeft().insert(node);
      }
    } else {
      if (this.getRight() == null) {
        node.setParent(this);
        this.setRight(node);
      } else {
        this.getRight().insert(node);
      }
    }
  }

  /**
   * Deletes this node itself from its Binary Search Tree.
   * 
   * @return The deleted node itself.
   */
  public BSTNode delete() {
    if (this.getLeft() == null || this.getRight() == null) {
      BSTNode tmpNode = this.getLeft() == null ? this.getRight() : null;
      if (this == this.getParent().getLeft()) {
        this.getParent().setLeft(tmpNode);
        if (this.getParent().getLeft() != null) {
          this.getParent().getLeft().setParent(this.getParent());
        }
      } else {
        this.getParent().setRight(tmpNode);
        if (this.getParent().getRight() != null) {
          this.getParent().getRight().setParent(this.getParent());
        }
      }
      return this;
    } else {
      BSTNode s = this.nextLarger();
      Integer tmp = this.getKey();
      this.setKey(s.getKey());
      s.setKey(tmp);
      return s.delete();
    }
  }

  /**
   * Retrieves its own key.
   * 
   * @return key.
   */
  public Integer getKey() {
    return key;
  }

  /**
   * Updates its own key.
   * 
   * @param key key to be used to update its key.
   */
  public void setKey(Integer key) {
    this.key = key;
  }

  /**
   * Retrieves its own parent node
   * 
   * @return node parent
   */
  public BSTNode getParent() {
    return parent;
  }

  /**
   * Updates its parent.
   * 
   * @param parent node to be set as a parent.
   */
  public void setParent(BSTNode parent) {
    this.parent = parent;
  }

  /**
   * Retrieves its left child node.
   * 
   * @return left child node.
   */
  public BSTNode getLeft() {
    return left;
  }

  /**
   * Updates its left child.
   * 
   * @param left node to be set as a left child.
   */
  public void setLeft(BSTNode left) {
    this.left = left;
  }

  /**
   * Retrieves its right child node.
   * 
   * @return left child node.
   */
  public BSTNode getRight() {
    return right;
  }

  /**
   * Updates its right child.
   * 
   * @param right node to be set as a right child.
   */
  public void setRight(BSTNode right) {
    this.right = right;
  }

  /**
   * Retrieves its height.
   * 
   * @return height.
   */
  public Integer getHeight() {
    return height;
  }

  /**
   * Updates its height. Only used for AVLs.
   * 
   * @param height Integer value to be set as a height.
   */
  public void setHeight(Integer height) {
    this.height = height;
  }
}
