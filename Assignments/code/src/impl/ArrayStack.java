package impl;

import core.Stack;

public class ArrayStack<E> implements Stack<E> {
	private E[] array;
	private int top;
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int size) {
		array = (E[]) new Object[size];
		top = 0;
	}
	
	public ArrayStack() {
		this(50);
	}
	
	public void push(E object) {
		if (top == array.length) throw new StackFullException();
		array[top] = object;
		top++;
	}

	public E pop() {
		if (top == 0) throw new StackEmptyException();
		top--;
		E temp =array[top];
		array[top] = null;
		return temp;
	}
	
	
	public int size() {
		return top;
	}
	
	public boolean isEmpty() {
		return top == 0;
	}

	public E top() {
		if (top == 0) throw new StackEmptyException();
		return array[top-1];		
	}	

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(top).append(" ./ ").append(array.length).append(" :");
		for (int i=0; i < top; i++) {
			buf.append(" ").append(array[i].toString());
		}
		return buf.toString();
	}
	
	public static void main(String[] args) {
		ArrayStack<String> stack = new ArrayStack<String>();
		System.out.println(stack);
		stack.push("D");
		System.out.println(stack);
		stack.push("O");
		System.out.println(stack);
		stack.push("G");
		System.out.println(stack);
		String x = stack.pop();
		System.out.println(x);
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
	}
}