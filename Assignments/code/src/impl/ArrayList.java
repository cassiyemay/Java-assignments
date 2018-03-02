package impl;

import java.lang.reflect.Array;
import java.util.Iterator;

import core.List;
import core.Position;

/**
 * ArrayPosition, and fields array / size are declared protected (not private)
 * so that we can refer to them in subclasses. This is because ArrayList is
 * extended to implement ArraySequence. Similar motivation is used to make
 * the insertAtIndex method protected (in practice, this is analogous to the
 * insertAtRank(...) method).
 * 
 * @author Rem
 *
 * @param <T>
 */
public class ArrayList<T> implements List<T> {
	protected class ArrayPosition implements Position<T> {
		int index;
		T element;
		
		public ArrayPosition(int index, T element) {
			this.index = index;
			this.element = element;
		}

		@Override
		public T element() {
			return element;
		}
	}

	protected ArrayPosition[] array;
	protected int size;
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		// Must use this method to create an array of size capacity as otherwise
		// the casting will not work (it results in a runtime exception).
		array = (ArrayPosition[]) Array.newInstance(ArrayPosition.class, capacity);
		size = 0;
	}
	
	public ArrayList() {
		this(20);
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
	public Position<T> first() {
		if (size == 0) throw new EmptyListException();
		return array[0];
	}

	@Override
	public Position<T> last() {
		if (size == 0) throw new EmptyListException();
		return array[size-1];
	}

	@Override
	public Position<T> prev(Position<T> p) {
		ArrayPosition pos = (ArrayPosition) p;
		if (pos.index == 0) throw new InvalidPositionException();
		return array[pos.index-1];
	}

	@Override
	public Position<T> next(Position<T> p) {
		ArrayPosition pos = (ArrayPosition) p;
		if (pos.index == size-1) throw new InvalidPositionException();
		return array[pos.index+1];
	}

	@SuppressWarnings("unchecked")
	protected Position<T> insertAtIndex(int index, T element) {
		if (size == array.length) {
			// extend the array
			ArrayPosition[] newArray = (ArrayPosition[]) new Object[array.length*2];
			for (int i=0; i<array.length;i++) newArray[i] = array[i];
			array = newArray;
		}
		
		// shift the existing values up (including an update of the index field
		// of each moved element.
		for (int i=size; i>index; i--) {
			array[i] = array[i-1];
			array[i].index = i;
		}
		
		// Insert the value
		array[index] = new ArrayPosition(index, element);
		size++;
		
		// Return the position...
		return array[index];
	}

	@Override
	public Position<T> insertFirst(T e) {
		return insertAtIndex(0, e);
	}

	@Override
	public Position<T> insertLast(T e) {
		return insertAtIndex(size, e);
	}

	@Override
	public Position<T> insertBefore(Position<T> p, T e) {
		ArrayPosition position = (ArrayPosition) p;
		if (position.index == -1) throw new InvalidPositionException();
		return insertAtIndex(position.index, e);
	}

	@Override
	public Position<T> insertAfter(Position<T> p, T e) {
		ArrayPosition position = (ArrayPosition) p;
		if (position.index == -1) throw new InvalidPositionException();
		return insertAtIndex(position.index+1, e);
	}

	@Override
	public T replace(Position<T> p, T e) {
		ArrayPosition position = (ArrayPosition) p;
		if (position.index == -1) throw new InvalidPositionException();
		T temp = position.element;
		position.element = e;
		return temp;
	}

	@Override
	public T remove(Position<T> p) {
		ArrayPosition position = (ArrayPosition) p;
		if (position.index == -1) throw new InvalidPositionException();
		
		// shift the existing values up (including an update of the index field
		// of each moved element.
		for (int i=position.index; i<size-1; i++) {
			array[i] = array[i+1];
			array[i].index = i;
		}
		
		// remove the duplicate entry left by shifting an set the
		// index of the removed position to -1 to indicate that it
		// is no longer in the list.
		array[size-1] = null;
		position.index = -1;
		size--;
		
		return position.element;
	}
	
	public String toString() {
		String out = size + " :";
		for (int i=0; i<size; i++) {
			out += " " + array[i].element;
		}
		return out;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public T next() {
				return array[index++].element;
			}
		};
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		Position<String> p = list.insertFirst("rem");
		System.out.println(list);
		list.insertFirst("happy");
		System.out.println(list);
		list.insertLast("beer");
		System.out.println(list);
		list.insertAfter(p, "likes");
		System.out.println(list);
		Position<String> p2 = list.insertBefore(p, "really");
		System.out.println(list);
		list.remove(p2);
		System.out.println(list);
		list.remove(p2);
	}
}
