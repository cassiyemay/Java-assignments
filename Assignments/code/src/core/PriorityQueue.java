package core;

public interface PriorityQueue<K, V> {
	public int size();

	public boolean isEmpty();

	public void insert(K key, V value);

	public V min();

	public V remove();
}
