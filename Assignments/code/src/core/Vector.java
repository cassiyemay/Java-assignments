package core;

public interface Vector<T> extends Iterable<T> {
	public int size();
	public boolean isEmpty();
	public T elemAtRank(int rank);
	public T replaceAtRank(int rank, T element);
	public void insertAtRank(int rank, T element);
	public T removeAtRank(int rank);
}
