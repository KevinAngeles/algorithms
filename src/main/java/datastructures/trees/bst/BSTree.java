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
package datastructures.trees.bst;

import datastructures.trees.bst.nodes.BSTNode;

public class BSTree {
  private BSTNode root;

  public BSTree() {
    this.setRoot(null);
  }

  /**
   * Finds the node with key k in this Binary Search Tree.
   * 
   * @param k The key of the node to be found.
   * @return The node with key k if found or null if not found.
   */
  public BSTNode find(Integer k) {
    if (this.getRoot() != null) {
      return this.getRoot().find(k);
    }
    return this.getRoot();
  }

  /**
   * Finds the node with the minimum key in this Binary Search Tree.
   * 
   * @return The node with the minimum key if found or null if the Binary Search
   *         Tree is empty.
   */
  public BSTNode findMin() {
    if (this.getRoot() != null) {
      return this.getRoot().findMin();
    }
    return this.getRoot();
  }

  /**
   * Finds the node with the maximum key in this Binary Search Tree.
   * 
   * @return The node with the maximum key if found or null if the Binary Search
   *         Tree is empty.
   */
  public BSTNode findMax() {
    if (this.getRoot() != null) {
      return this.getRoot().findMax();
    }
    return this.getRoot();
  }

  /**
   * Inserts a node with key k into this Binary Search Tree.
   * 
   * @return The node with the minimum key if found or null if the Binary Search
   *         Tree is empty.
   */
  public BSTNode insert(Integer k) {
    BSTNode node = new BSTNode(null, k);

    if (this.getRoot() == null) {
      this.setRoot(node);
    } else {
      this.getRoot().insert(node);
    }

    return node;
  }

  /**
   * Deletes the node with key k from this Binary Search Tree if it exists.
   *
   * @param k The key of the node to be deleted.
   * @return The node that was deleted or null if the Binary Search Tree is empty.
   */
  public BSTNode delete(Integer k) {
    BSTNode node = this.find(k);
    if (node == null) {
      return null;
    }

    if (node == this.getRoot()) {
      BSTNode pseudoroot = new BSTNode(null, 0);
      pseudoroot.setLeft(this.getRoot());
      this.getRoot().setParent(pseudoroot);
      BSTNode deleted = this.getRoot().delete();
      this.setRoot(pseudoroot.getLeft());
      if (this.getRoot() != null) {
        this.getRoot().setParent(null);
      }
      return deleted;
    }
    return node.delete();
  }

  /**
   * Find the node with the next smaller key in this Binary Search Tree.
   * 
   * @param k The key of the node to be used as a base to find its precessor in
   *          this Binary Search Tree.
   * @return The precessor of node with key k or null if there is no precessor.
   */
  public BSTNode nextSmaller(Integer k) {

    BSTNode node = this.find(k);

    return node != null ? node.nextSmaller() : node;
  }

  /**
   * Find the node with the next larger key in this Binary Search Tree.
   * 
   * @param k The key of the node to be used as a base to find its successor in
   *          this Binary Search Tree.
   * @return The successor of node with key k or null if there is no successor.
   */
  public BSTNode nextLarger(Integer k) {
    BSTNode node = this.find(k);

    return node != null ? node.nextLarger() : node;
  }

  /**
   * Retrieves root node.
   * 
   * @return root node.
   */
  public BSTNode getRoot() {
    return root;
  }

  /**
   * Sets a node as a root.
   * 
   * @param root node to be set as root.
   */
  public void setRoot(BSTNode root) {
    this.root = root;
  }
}