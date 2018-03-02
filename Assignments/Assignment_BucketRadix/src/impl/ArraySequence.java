package impl;

public class ArraySequence<T> extends ArrayList<T> implements Sequence<T> {
	public ArraySequence(int num){
		super(num);
	}
	public ArraySequence(){
		super();
	}
	
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
}
