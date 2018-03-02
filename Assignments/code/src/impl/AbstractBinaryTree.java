package impl;

import java.util.Iterator;

import core.BinaryTree;
import core.List;
import core.Position;
import core.Visitor;

public abstract class AbstractBinaryTree<T> implements BinaryTree<T> {
	protected class Node implements Position<T> {
		Node parent;
		Node left;
		Node right;
		T element;
		
		public Node(Node parent, Node left, Node right, T element) {
			this.parent = parent;
			this.left = left;
			this.right = right;
			this.element = element;
		}

		@Override
		public T element() {
			return element;
		}
		
		public String toString() {
			if (element == null) return "null";
			return element.toString();
		}
	}
	
	protected Node root;
	protected int size = 0;

	@Override
	public Position<T> root() {
		return root;
	}

	@Override
	public Position<T> parent(Position<T> p) {
		Node node = (Node) p;
		return node.parent;
	}

	@Override
	public Iterator<Position<T>> children(Position<T> p) {
		List<Position<T>> list = new LinkedList<Position<T>>();
		if (hasLeft(p)) list.insertLast(left(p));
		if (hasRight(p)) list.insertLast(right(p));
		return list.iterator();
	}

	@Override
	public boolean isInternal(Position<T> p) {
		Node node = (Node) p;
		return (node.left != null) || (node.right != null);
	}

	@Override
	public boolean isExternal(Position<T> p) {
		Node node = (Node) p;
		return (node.left == null) && (node.right == null);
	}

	@Override
	public boolean isRoot(Position<T> p) {
		return root == p;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Uses the position iterator and returns the element at each position.
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Iterator<Position<T>> iterator = positions();
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public T next() {
				return iterator.next().element();
			}
		};
	}

	/**
	 * Iterator over all the positions in the Tree. Uses a Visitor that
	 * implements a pre-order traversal of the tree, storing the values
	 * in the list. An iterator to the list is then used to iterate through
	 * the positions
	 */
	@Override
	public Iterator<Position<T>> positions() {
		return new Iterator<Position<T>>() {
			Iterator<Position<T>> iterator;
			
			Visitor<T> visitor = new Visitor<T>() {
				@SuppressWarnings("unchecked")
				@Override
				public void visit(Position<T> p, Object data) {
					((List<Position<T>>) data).insertLast(p);
					Iterator<Position<T>> it = children(p);
					while (it.hasNext()) {
						visit(it.next(), data);
					}
				}
			};
			
			// Static initialisation block - invokes the visit() method on the
			// root node, which will recurisvely visit every other node.
			{
				List<Position<T>> list = new LinkedList<Position<T>>();
				visitor.visit(root,list);
				iterator = list.iterator();
			}
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Position<T> next() {
				return iterator.next();
			}
		};
	}

	@Override
	public T replace(Position<T> p, T t) {
		Node node = (Node) p;
		T temp = node.element;
		node.element = t;
		return temp;
	}

	@Override
	public Position<T> left(Position<T> p) {
		if (!hasLeft(p)) throw new InvalidPositionException();
		Node node = (Node) p;
		return node.left;
	}

	@Override
	public Position<T> right(Position<T> p) {
		if (!hasRight(p)) throw new InvalidPositionException();
		Node node = (Node) p;
		return node.right;
	}

	@Override
	public boolean hasLeft(Position<T> p) {
		Node node = (Node) p;
		return node.left != null;
	}

	@Override
	public boolean hasRight(Position<T> p) {
		Node node = (Node) p;
		return node.right != null;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		// Seriously inline anonymous class :o)
		new Visitor<T>() {
			public void visit(Position<T> position, Object data) {
				buf.append(data.toString());
				buf.append(position.element());
				buf.append("\n");
				
				Iterator<Position<T>> it = children(position);
				while (it.hasNext()) {
					visit(it.next(), data+"\t");
				}
			}
		}.visit(root, "");
		
		return buf.toString();
	}
}
