package impl;

import core.Queue;

public class ArrayQueue implements Queue {
	private Object[] array;
	private int front, rear;
	
	public ArrayQueue(int size) {
		array = new Object[size];
		front = rear = 0;
	}
	
	public ArrayQueue() {
		this(50);
	}

	@Override
	public void enqueue(Object object) {
		if (size() == array.length-1) throw new QueueFullException();
		array[rear] = object;
		rear = (rear+1) % array.length;
	}

	@Override
	public Object dequeue() {
		if (isEmpty()) throw new QueueEmptyException();
		Object temp = array[front];
		array[front] = null;
		front = (front+1) % array.length;
		return temp;
	}

	@Override
	public Object front() {
		if (isEmpty()) throw new QueueEmptyException();
		return array[front];
	}

	@Override
	public boolean isEmpty() {
		return front==rear;
	}

	@Override
	public int size() {
		return (array.length+rear-front) % array.length;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(size()).append(" / ").append(array.length).append(" :");
		int i = front;
		while (i != rear) {
			buf.append(" ").append(array[i]);
			i = (i+1) % array.length;
		}
		return buf.toString();
	}
	
	public static void main(String[] args) {
		Queue queue = new ArrayQueue();
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
