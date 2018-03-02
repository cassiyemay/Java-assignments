package impl;

import java.lang.reflect.Array;
import java.util.Iterator;

import core.BinaryTree;
import core.List;
import core.Position;
import core.Visitor;

public class CompleteBinaryTree<T> implements BinaryTree<T> {
	private class ArrayPosition implements Position<T> {
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
		
		public String toString() {
			return index+"/"+element.toString();
		}
	}
	
	private ArrayPosition[] array;
	private int last;
	
	public CompleteBinaryTree() {
		this(50);
	}
	
	@SuppressWarnings("unchecked")
	public CompleteBinaryTree(int capacity) {
		array = (ArrayPosition[]) Array.newInstance(ArrayPosition.class, capacity);
		last = 1;
	}
	
	@Override
	public Position<T> root() {
		if (isEmpty()) throw new InvalidPositionException();
		return array[1];
	}

	@Override
	public Position<T> parent(Position<T> p) {
		if (p == root()) throw new InvalidPositionException();
		
		return array[((ArrayPosition) p).index/2];
	}

	@Override
	public Iterator<Position<T>> children(Position<T> p) {
		int index = ((ArrayPosition) p).index*2;
		
		List<Position<T>> list = new LinkedList<Position<T>>();
		if (array[index] != null) list.insertLast(array[index]);
		if (array[index+1] != null) list.insertLast(array[index+1]);
//		System.out.println(list);
		return list.iterator();
	}

	@Override
	public boolean isInternal(Position<T> p) {
		int index = ((ArrayPosition) p).index*2;

		return (array[index] != null) || (array[index+1] != null);
	}

	@Override
	public boolean isExternal(Position<T> p) {
		return !isInternal(p);
	}

	@Override
	public boolean isRoot(Position<T> p) {
		return p == array[1];
	}

	@Override
	public int size() {
		return last-1;
	}

	@Override
	public boolean isEmpty() {
		return last==1;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Iterator<Position<T>> iterator = positions();
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public T next() {
				return iterator.next().element();
			}
		};
	}

	@Override
	public Iterator<Position<T>> positions() {
		return new Iterator<Position<T>>() {
			Iterator<Position<T>> iterator;
			
			
			Visitor<T> visitor = new Visitor<T>() {
				@SuppressWarnings("unchecked")
				@Override
				public void visit(Position<T> p, Object data) {
					((List<Position<T>>) data).insertLast(p);
					Iterator<Position<T>> it = children(p);
					while (it.hasNext()) {
						visit(it.next(), data);
					}
				}
			};
			
			// Static initialisation block - invokes the visit() method on the
			// root node, which will recurisvely visit every other node.
			{
				List<Position<T>> list = new LinkedList<Position<T>>();
				visitor.visit(root(),list);
				iterator = list.iterator();
			}
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Position<T> next() {
				return iterator.next();
			}
		};
	}

	@Override
	public T replace(Position<T> p, T t) {
		T temp = p.element();
		((ArrayPosition) p).element = t;
		return temp;
	}

	@Override
	public Position<T> left(Position<T> p) {
		int index = ((ArrayPosition) p).index*2;
		if (index >= array.length) return null;
		return array[index];
	}

	@Override
	public Position<T> right(Position<T> p) {
		int index = ((ArrayPosition) p).index*2 + 1;
		if (index >= array.length) return null;
		return array[index];
	}

	@Override
	public boolean hasLeft(Position<T> p) {
		int index = ((ArrayPosition) p).index*2;
		if (index >= array.length) return false;
		return array[index] != null;
	}

	@Override
	public boolean hasRight(Position<T> p) {
		int index = ((ArrayPosition) p).index*2 + 1;
		if (index >= array.length) return false;
		return array[index] != null;
	}

	@SuppressWarnings("unchecked")
	public Position<T> add(T t) {
		if (last == array.length) {
			// extend array
			ArrayPosition[] temp = (ArrayPosition[]) Array.newInstance(ArrayPosition.class, array.length);
			for (int i=1; i < array.length; i++) {
				temp[i] = array[i];
			}
			array = temp;
		}
		
		array[last] = new ArrayPosition(last, t);
		return array[last++];
	}
	
	public T remove() {
		Position<T> temp = array[last-1];
		array[last-1] = null;
		last--;
		return temp.element();
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();

		for (int i=1; i< last; i++) {
			buf.append(" ").append(array[i]);
		}
		
		return buf.toString();		
	}
	
	public String toTreeString() {
		StringBuffer buf = new StringBuffer();

		// Seriously inline anonymous class :o)
		new Visitor<T>() {
			public void visit(Position<T> position, Object data) {
				buf.append(data.toString());
				buf.append(position.element());
				buf.append("\n");
				
				Iterator<Position<T>> it = children(position);
				while (it.hasNext()) {
					visit(it.next(), data+"\t");
				}
			}
		}.visit(root(), "");
		
		return buf.toString();		
	}
}
