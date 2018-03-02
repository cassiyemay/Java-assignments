package core;

public interface BinaryTree<T> extends Tree<T> {
    public Position<T> left(Position<T> p);
    public Position<T> right(Position<T> p);
    public boolean hasLeft(Position<T> p);
    public boolean hasRight(Position<T> p);
}
