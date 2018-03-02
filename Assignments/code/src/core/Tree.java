package core;

import java.util.Iterator;

public interface Tree<T> {
    public Position<T> root();
    public Position<T> parent(Position<T> p);
    public Iterator<Position<T>> children(Position<T> p);
    public boolean isInternal(Position<T> p);
    public boolean isExternal(Position<T> p);
    public boolean isRoot(Position<T> p);
    public int size();
    public boolean isEmpty();   
    public Iterator<T> iterator();
    public Iterator<Position<T>> positions();
    public T replace(Position<T> p, T t);
}
