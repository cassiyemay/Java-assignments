package impl;

import core.PriorityQueue;

public class HeapPriorityQueue<P extends Comparable<P>, V> implements PriorityQueue<P, V> {
	private class HeapEntry implements Comparable<HeapEntry> {
		P priority;
		V value;
		
		public HeapEntry(P priority, V value) {
			this.priority = priority;
			this.value = value;
		}
		
		@Override
		public int compareTo(HeapEntry entry) {
			return priority.compareTo(entry.priority);
		}
		
		public String toString() {
			return "<" + priority + "," + value + ">";
		}
	}
	
	Heap<HeapEntry> heap = new Heap<HeapEntry>();
	
	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	@Override
	public void insert(P priority, V value) {
		heap.add(new HeapEntry(priority, value));

	}

	@Override
	public V min() {
		return heap.min().value;
	}

	@Override
	public V remove() {
		return heap.remove().value;
	}

	public String toString() {
		return heap.toString();
	}
}
