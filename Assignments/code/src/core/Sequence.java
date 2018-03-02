package core;

public interface Sequence<T> extends List<T>, Vector<T> {
	public int toRank(Position<T> p);
	public Position<T> fromRank(int rank);
}
