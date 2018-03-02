package core;

public interface Queue {
	public void enqueue(Object object);
	public Object dequeue();
	public Object front();
	public boolean isEmpty();
	public int size();
}
