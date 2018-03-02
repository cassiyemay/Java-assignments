package impl;

import java.util.Iterator;

import core.List;
import core.Position;
import core.Visitor;

/**
 * This class implements a BinarySearchTree for some type T. T must implement 
 * Comparable<T>.
 * 
 * Some of the methods may seem a little wierd (e.g. return the value stored
 * at a node when found by the same value, but this is done so that you can
 * use the tree in more complex scenarios - such as a TreeMap - hint hint).
 * 
 * @author Rem
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T> {
	private ProperBinaryTree<T> tree;
	private int size;
	
	public BinarySearchTree() {
		tree = new ProperBinaryTree<T>();
		size = 0;
	}
	
	/**
	 * Core method that implements the search algorithm for
	 * a binary search tree.
	 * 
	 * @param p
	 * @param value
	 * @return
	 */
	private Position<T> find(Position<T> p, T value) {
		if (tree.isExternal(p)) return p;

		int result = value.compareTo(p.element());
		if (result < 0) {
			return find(tree.left(p), value);
		} else if (result == 0) {
			return p;
		} else {
			return find(tree.right(p), value);
		}
	}
	
	/**
	 * Inserts value into the tree. If value is not in the
	 * tree then it is added and null is returned. Otherwise
	 * the current value stored at the internal node should be
	 * returned.
	 * 
	 * @param value
	 * @return
	 */
	public T insert(T value) {
		Position<T> p = find(tree.root(), value);
		if (tree.isExternal(p)) {
			tree.expandExternal(p);
			tree.replace(p, value);
			size++;
			return null;
		} else {
			return tree.replace(p, value);
		}
	}

	/**
	 * Search for a node containing value. If no node exists, return null.
	 * Otherwise return the value stored at the node.
	 * @param value
	 * @return
	 */
	public T find(T value) {
		Position<T> p = find(tree.root(), value);
		if (tree.isExternal(p)) {
			return null;
		} else {
			return p.element();
		}
	}

	/**
	 * Search for a node containing value. If no node exists, return null.
	 * Otherwise remove the node containing value and return the old value.
	 * @param value
	 * @return
	 */
	public T remove(T value) {
		Position<T> p = find(tree.root(), value);
		if (tree.isExternal(p)) {
			return null;
		}
		
		T temp = p.element();
		
		// collapseInternal works for 2 external children or 1 external child.
		// It does not work for 2 internal children, but there is a solution for
		// this. 
		if (tree.isInternal(tree.left(p)) && tree.isInternal(tree.right(p))) {
			// get the right child (it must exist because both children are
			// internal)
			Position<T> pos = tree.right(p);
			
			// Go left till there is no child
			while (tree.hasLeft(pos)) pos = tree.left(pos);
			
			// This is an external node, so the parent is an internal node
			// the has at most 1 internal child. It is also the next node
			// to be visited after p in an in-order traversal.
			pos = tree.parent(pos);
			
			// copy the value to position p, and remove node at position
			// pos (reduce size by 1).
			tree.replace(p, pos.element());
			tree.collapseInternal(pos);
			size--;
		} else {
			tree.collapseInternal(p);
		}
		
		return temp;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public String toString() {
		return tree.toString();
	}
	
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Iterator<T> iterator;
			
			Visitor<T> visitor = new Visitor<T>() {
				@SuppressWarnings("unchecked")
				@Override
				public void visit(Position<T> p, Object data) {
					if (tree.isInternal(p)) {
						((List<T>) data).insertLast(p.element());
						Iterator<Position<T>> it = tree.children(p);
						while (it.hasNext()) {
							visit(it.next(), data);
						}
					}
				}
			};
			
			// Static initialisation block - invokes the visit() method on the
			// root node, which will recursively visit every other node.
			{
				List<T> list = new LinkedList<T>();
				visitor.visit(tree.root(),list);
				iterator = list.iterator();
			}
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public T next() {
				return iterator.next();
			}
		};
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		tree.insert(12);
		System.out.println(tree);
		tree.insert(5);
		System.out.println(tree);
		tree.insert(15);
		System.out.println(tree);
		tree.insert(7);
		System.out.println(tree);
		tree.remove(12);
		System.out.println(tree);
		tree.remove(5);
		System.out.println(tree);
	}
}
