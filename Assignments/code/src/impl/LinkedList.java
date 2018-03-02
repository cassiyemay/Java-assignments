package impl;

import java.util.Iterator;

import core.List;
import core.Position;

public class LinkedList<T> implements List<T> {
	protected class Node implements Position<T> {
		T element;
		Node prev;
		Node next;

		public Node(T element, Node prev, Node next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}

		public T element() {
			return element;
		}
	}

	protected Node front;
	protected Node rear;
	protected int size = 0;

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<T> first() {
		if (size == 0)
			throw new EmptyListException();
		return front;
	}

	@Override
	public Position<T> last() {
		if (size == 0)
			throw new EmptyListException();
		return rear;
	}

	@Override
	public Position<T> prev(Position<T> p) {
		Node current = (Node) p;

		// Check that the next / prev links of the node are not null
		// if they are, and the node is not the front node, then this
		// node was previously deleted from the list...
		if (current.next == null && current.prev == null && current != front)
			throw new InvalidPositionException();

		return current.prev;
	}

	@Override
	public Position<T> next(Position<T> p) {
		Node current = (Node) p;

		// Check that the next / prev links of the node are not null
		// if they are, and the node is not the front node, then this
		// node was previously deleted from the list...
		if (current.next == null && current.prev == null && current != front)
			throw new InvalidPositionException();

		return current.next;
	}

	@Override
	public Position<T> insertFirst(T e) {
		Node node = new Node(e, null, front);
		if (rear == null) {
			rear = node;
		} else {
			front.prev = node;
		}
		front = node;
		size++;
		return node;
	}

	@Override
	public Position<T> insertLast(T e) {
		Node node = new Node(e, rear, null);
		if (front == null) {
			front = node;
		} else {
			rear.next = node;
		}
		rear = node;
		size++;
		return node;
	}

	@Override
	public Position<T> insertBefore(Position<T> p, T e) {
		if (p == front)
			return insertFirst(e);
		Node current = (Node) p;

		// Check that the next / prev links of the node are not null
		// if they are, and the node is not the front node, then this
		// node was previously deleted from the list...
		if (current.next == null && current.prev == null)
			throw new InvalidPositionException();

		Node node = new Node(e, current.prev, current);
		current.prev.next = node;
		current.prev = node;
		size++;
		return node;
	}

	@Override
	public Position<T> insertAfter(Position<T> p, T e) {
		if (p == rear)
			return insertLast(e);
		Node current = (Node) p;

		// Check that the next / prev links of the node are not null
		// if they are, and the node is not the rear node, then this
		// node was previously deleted from the list...
		if (current.next == null && current.prev == null)
			throw new InvalidPositionException();

		Node node = new Node(e, current, current.next);
		current.next.prev = node;
		current.next = node;
		size++;
		return node;
	}

	@Override
	public T replace(Position<T> p, T e) {
		Node node = (Node) p;
		T temp = node.element;
		node.element = e;
		return temp;
	}

	@Override
	public T remove(Position<T> p) {
		if (size == 0)
			throw new EmptyListException();

		Node current = (Node) p;

		// Check that the next / prev links of the node are not null
		// if they are, and the node is not the front node, then this
		// node was previously deleted from the list...
		if (current.next == null && current.prev == null && current != front)
			throw new InvalidPositionException();

		if (front == current) {
			front = current.next;
		} else {
			current.prev.next = current.next;
		}
		if (rear == current) {
			rear = current.prev;
		} else {
			current.next.prev = current.prev;
		}
		current.prev = null;
		current.next = null;

		size--;
		return current.element;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(size + " :");
		Node current = front;
		while (current != null) {
			buf.append(" " + current.element);
			current = current.next;
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();
		Position<String> p = list.insertFirst("rem");
		System.out.println(list);
		list.insertFirst("happy");
		System.out.println(list);
		list.insertLast("beer");
		System.out.println(list);
		list.insertAfter(p, "likes");
		System.out.println(list);
		Position<String> p2 = list.insertBefore(p, "really");
		System.out.println(list);
		list.remove(p2);
		System.out.println(list);
		list.remove(p2);
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node current = front;

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
