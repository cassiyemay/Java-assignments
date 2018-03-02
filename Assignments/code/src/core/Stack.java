package core;

public interface Stack<T> {
	public void push(T object);
	public T pop();
	public T top();
	public boolean isEmpty();
	public int size();
}
