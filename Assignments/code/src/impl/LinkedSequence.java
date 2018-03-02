package impl;

import core.Position;
import core.Sequence;

public class LinkedSequence<T> extends LinkedList<T> implements Sequence<T> {
	@Override
	public T elemAtRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		return fromRank(rank).element();
	}

	@Override
	public T replaceAtRank(int rank, T element) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		return replace(fromRank(rank), element);
	}

	@Override
	public void insertAtRank(int rank, T element) {
		if (rank < 0 || rank > size) throw new RankOutOfBoundsException();
		insertBefore(fromRank(rank), element);
	}

	@Override
	public T removeAtRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		
		return remove(fromRank(rank));
	}

	@Override
	public int toRank(Position<T> p) {
		int index = 0;
		Node current = front;
		while (current != p) {
			current = current.next;
			if (current == null) throw new InvalidPositionException();
			index++;
		}
		return index;
	}

	@Override
	public Position<T> fromRank(int rank) {
		if (rank < 0 || rank > size-1) throw new RankOutOfBoundsException();
		Node current = front;
		for (int i=0; i<rank; i++) current = current.next;
		return current;
	}

	public static void main(String[] args) {
		Sequence<String> list = new LinkedSequence<String>();
		Position<String> p = list.insertFirst("rem");
		System.out.println(list);
		list.insertFirst("happy");
		System.out.println(list);
		list.insertLast("beer");
		System.out.println(list);
		list.insertAfter(p, "likes");
		System.out.println(list);
		Position<String> p2 = list.insertAfter(p, "really");
		System.out.println(list);
		list.remove(p2);
		System.out.println(list);
		list.insertAtRank(2, "collier");
		System.out.println(list);
		list.removeAtRank(0);
		System.out.println(list);
	}
}
