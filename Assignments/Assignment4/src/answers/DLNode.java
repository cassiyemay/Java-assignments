package answers;

public class DLNode<E> {
	private E element;
	private DLNode<E> next, prev;
	
	public DLNode(){
		next=null;
		prev= null;
		element=null;
	}
	public DLNode(E e){
		element=e;
	}
	public void setElement(E newElem){
		element = newElem;
	}
	public void setNext(DLNode<E> newNext){
		next = newNext;
	}
	public void setPrev(DLNode<E> newPrev){
		prev = newPrev;
	}
	public E getElement() { return element; }
	public DLNode<E> getNext() { return next; }
	public DLNode<E> getPrev() { return prev; }
	
	public String toString(){
		return element.toString();
	}
}
