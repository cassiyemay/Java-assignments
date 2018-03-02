package impl;

import java.util.Iterator;

import core.Vector;

public class ArrayVector<E> implements Vector<E> {
	private E[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayVector(int capacity) {
		array = (E[]) new Object[capacity];
		size = 0;
	}

	public ArrayVector() {
		this(50);
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E elemAtRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		return array[rank];
	}

	@Override
	public E replaceAtRank(int rank, E element) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		E temp = array[rank];
		array[rank] = element;
		return temp;
	}

	@Override
	public void insertAtRank(int rank, E element) {
		if (rank < 0 || rank > size) throw new RankOutOfBoundsException();

		// Extend the array to increase the capacity of the Vector
		if (size == array.length) {
			E[] newArray = (E[]) new Object[array.length*2];
			for (int i=0; i < array.length; i++) newArray[i] = array[i];
			array = newArray;
		}
		
		for (int i=size; i > rank; i--) {
			array[i] = array[i-1];
		}
		array[rank] = element;
		size++;
	}

	@Override
	public E removeAtRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		E temp = array[rank];
		for (int i=rank; i < size-1; i++) {
			array[i] = array[i+1];
		}
		array[size-1] = null;
		size--;
		return temp;
	}

	public String toString() {
		String out = size + " / " + array.length + ":";
		for (int i=0; i<size; i++) {
			out += " " + array[i];
		}
		return out;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < size();
			}

			@Override
			public E next() {
				return elemAtRank(index++);
			}
		};

	}

	public static void main(String[] args) {
		Vector<Integer> vector = new ArrayVector<Integer>();
		
		for (int i=0; i<21; i++) {
			vector.insertAtRank(0, i);
			System.out.println(vector);
		}
		
		Iterator<Integer> it = vector.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
