package impl;

import java.util.Iterator;

import core.Vector;

public class SinglyLinkedVector<T> implements Vector<T> {
	private class Node {
		T element;
		Node next;
		
		public Node(T element, Node next) {
			this.element = element;
			this.next = next;
		}
	}
	
	private Node front;
	private int size = 0;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public T elemAtRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		// Search for the insertion point
		int i=0;
		Node current = front;
		while (i < rank) {
			current = current.next;
			i++;
		}
		return current.element;
	}

	@Override
	public T replaceAtRank(int rank, T element) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		// Search for the insertion point
		int i=0;
		Node current = front;
		while (i < rank) {
			current = current.next;
			i++;
		}
		T temp = current.element;
		current.element = element;
		return temp;
	}

	@Override
	public void insertAtRank(int rank, T element) {
		if (rank < 0 || rank > size) throw new RankOutOfBoundsException();
		if (rank == 0) {
			front = new Node(element, front);
		} else {
			// Search for the insertion point
			int i=0;
			Node current = front;
			Node prev = null;
			while (i < rank) {
				prev = current;
				current = current.next;
				i++;
			}
			
			// prev is now the node with rank 1 less than the insertion rank
			prev.next = new Node(element, current);
		}
		size++;
	}

	@Override
	public T removeAtRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		// Search for the insertion point
		int i=0;
		Node current = front;
		Node prev = null;
		while (i < rank) {
			prev = current;
			current = current.next;
			i++;
		}
		T temp  = current.element;
		if (rank == 0) {
			front = front.next;
		} else {
			prev.next = current.next;
		}
		current.next = null;
		size--;
		return temp;
	}

	public String toString() {
		String out = size + " :";
		Node current = front;
		while (current != null) {
			out += " " + current.element;
			current = current.next;
		}
		return out;
	}
	
	public static void main(String[] args) {
		Vector<String> vector = new SinglyLinkedVector<String>();
		System.out.println(vector);
		vector.insertAtRank(0, "L");
		System.out.println(vector);
		vector.insertAtRank(1, "L");
		System.out.println(vector);
		vector.insertAtRank(0, "A");
		System.out.println(vector);
		vector.insertAtRank(3, "O");
		System.out.println(vector);
		vector.insertAtRank(0, "E");
		System.out.println(vector);
		vector.insertAtRank(0, "H");
		System.out.println(vector);
		vector.removeAtRank(2);
		System.out.println(vector);
		
		for (String val : vector) {
			System.out.println("val: " + val);
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node current=front;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T temp = current.element;
				current = current.next;
				return temp;
			}
		};
	}
}
