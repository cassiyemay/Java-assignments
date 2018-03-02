package impl;

import core.Queue;

public class LinkedQueue implements Queue {
	private class Node {
		Object element;
		Node next;
		
		public Node(Object element, Node next) {
			this.element = element;
			this.next = next;
		}
	}
	private Node rear = null;
	private Node front = null;
	private int size = 0;
	
	@Override
	public void enqueue(Object object) {
		Node node = new Node(object, null);
		if (front == null) {
			front = node;
		} else {
			rear.next = node;
		}
		rear = node;
		size++;
	}

	@Override
	public Object dequeue() {
		if (front == null) throw new QueueEmptyException();
		Node temp = front;
		front = front.next;
		temp.next = null;
		if (front == null) rear = null;
		size--;
		return temp.element;
	}

	@Override
	public Object front() {
		if (front == null) throw new QueueEmptyException();
		return front.element;
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return rear == null;
	}

	public String toString() {
		String output = "";
		if (front != null) {
			Node current = front;
			while (current != rear) {
				output += " " + current.element; 
				current = current.next;
			}
			output += " " + current.element; 
		}
		return size + " :" + output;
	}

	public static void main(String[] args) {
		Queue queue = new LinkedQueue();
		System.out.println(queue);
		queue.enqueue("R");
		System.out.println(queue);
		queue.enqueue("E");
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);
		queue.enqueue("M");
		System.out.println(queue);
		queue.enqueue("H");
		System.out.println(queue);
		queue.enqueue("E");
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);
		queue.enqueue("L");
		System.out.println(queue);
		queue.enqueue("L");
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);
		queue.enqueue("O");
		System.out.println(queue);
	}
}
