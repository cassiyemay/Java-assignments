package impl;

import java.util.Iterator;

import core.List;
import core.Position;

public class Heap<T extends Comparable<T>> {
	public static <T extends Comparable<T>> void sort(List<T> in, List<T> out) {
		Heap<T> heap = new Heap<T>();
		Iterator<T> it = in.iterator();
		while (it.hasNext()) {
			heap.add(it.next());
		}

		while (!heap.isEmpty()) {
			out.insertLast(heap.remove());
		}
	}
	
	private CompleteBinaryTree<T> tree = new CompleteBinaryTree<T>();

	public void add(T t) {
		// Step 1: insert at last position
		Position<T> p = tree.add(t);
		
		// Step 2: upheap
		while (!tree.isRoot(p) && p.element().compareTo(tree.parent(p).element()) < 0) {
			T temp = p.element();
			tree.replace(p, tree.parent(p).element());
			tree.replace(tree.parent(p), temp);
			p = tree.parent(p);
		}
		
		// Step 3: Update last - don't worry - we recorded this in step 1.
	}

	public T min() {
		if (tree.isEmpty()) throw new HeapEmptyException();
		return tree.root().element();
	}
	
	public T remove() {
		if (tree.isEmpty()) throw new HeapEmptyException();
		
		// Step 1: Remove the min value
		T temp = tree.root().element();
		tree.replace(tree.root(), tree.remove());
		
		if (!tree.isEmpty()) {
			// Step 2: Downheap
			Position<T> p = tree.root();
			while (tree.isInternal(p) && 
					((tree.hasLeft(p) && p.element().compareTo(tree.left(p).element()) > 0) || 
					(tree.hasRight(p) && p.element().compareTo(tree.right(p).element()) > 0))) {
				
				Position<T> child = null;
				if (!tree.hasLeft(p)) {
					child = tree.right(p);
				} else if (!tree.hasRight(p)) {
					child = tree.left(p);
				} else if (tree.left(p).element().compareTo(tree.right(p).element()) < 0) {
					child = tree.left(p);
				} else {
					child = tree.right(p);
				}
	
				T t = p.element();
				tree.replace(p, child.element());
				tree.replace(child, t);
				p = child;
			}
		}
		return temp;
	}

	public int size() {
		return tree.size();
	}
	
	public boolean isEmpty() {
		return tree.isEmpty();
	}
	
	public String toString() {
		return tree.toString();
	}
}
