package impl;

import core.Stack;

public class LinkedStack implements Stack {
	private class Node {
		Object value;
		Node next;

		public Node(Object value, Node next) {
			this.value = value;
			this.next = next;
		}
	}

	
	private Node top = null;
	private int size = 0;
	
	public void push(Object object) {
		Node node = new Node(object, top);
		top = node;
		size++;
	}
	
	public Object pop() {
		if (top == null) throw new StackEmptyException();
		Node temp = top;
		top = top.next;
		temp.next = null;
		size--;
		return temp.value;
	}
	
	public Object top() {
		if (top == null) throw new StackEmptyException();
		return top.value;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return top == null;
	}

	public String toString() {
		String output = "";
		Node current = top;
		while (current != null) {
			output = " " + current.value + output; 
			current = current.next;
		}
		return size + " :" + output;
	}

	public static void main(String[] args) {
		Stack stack = new LinkedStack();
		System.out.println(stack);
		stack.push("D");
		System.out.println(stack);
		stack.push("O");
		System.out.println(stack);
		stack.push("G");
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
	}
}
