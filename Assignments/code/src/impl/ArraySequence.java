package impl;

import java.util.Iterator;

import core.List;
import core.Position;
import core.Sequence;

public class ArraySequence<T> extends ArrayList<T> implements Sequence<T> {
	
	@Override
	public T elemAtRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		return array[rank].element;
	}

	@Override
	public T replaceAtRank(int rank, T element) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		T temp = array[rank].element;
		array[rank].element = element;
		return temp;
	}

	@Override
	public void insertAtRank(int rank, T element) {
		if (rank < 0 || rank > size) throw new RankOutOfBoundsException();
		insertAtIndex(rank, element);
	}

	@Override
	public T removeAtRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		return remove(array[rank]);
	}

	@Override
	public int toRank(Position<T> p) {
		ArrayPosition node = (ArrayPosition) p;
		if (node.index == -1) throw new InvalidPositionException();
		return node.index;
	}

	@Override
	public Position<T> fromRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		return array[rank];
	}

	public static void main(String[] args) {
		Sequence<String> list = new ArraySequence<String>();
		Position<String> p = list.insertFirst("rem");
		System.out.println(list);
		list.insertFirst("happy");
		System.out.println(list);
		list.insertLast("beer");
		System.out.println(list);
		list.insertAfter(p, "likes");
		System.out.println(list);
		Position<String> p2 = list.insertAfter(p, "really");
		//System.out.println(list);
		//list.remove(p2);
		//System.out.println(list);
		//list.insertAtRank(2, "collier");
		//System.out.println(list);
		//list.removeAtRank(0);
		//System.out.println(list);
		//list.remove(list.first());
	}
}
