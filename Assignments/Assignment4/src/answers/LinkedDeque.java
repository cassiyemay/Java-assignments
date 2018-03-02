package answers;

public class LinkedDeque<E> implements Deque<E> {
	protected DLNode<E> front, rear;
	protected int size;
	
	public LinkedDeque(){
		front = new DLNode<E>();
		rear = new DLNode<E>();
		front.setNext(rear);
		rear.setNext(front);
		size =0;
	}
	
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return (size()==0);
	}
	
	public E getFirst() throws EmptyDequeException {
		if(isEmpty()) throw new EmptyDequeException("Deque is empty");
		return front.getElement();
	}
	
	public E getLast() throws EmptyDequeException {
		if (isEmpty())
		      throw new EmptyDequeException("Deque is empty.");
		return rear.getElement();
	}
	
	public void addFirst (E element) {
		DLNode<E> node = new DLNode(element);
		node.setNext(front);
		if (front==null)
			rear=node;
		else
			front.setPrev(node);
		front=node;
		size++;
	}
	public void addLast (E element) {
		DLNode<E> node = new DLNode(element);
		node.setPrev(rear);
		if (rear==null)
			front=node;
		else
		rear.setNext(node);
		rear = node;
		size++;
	}
	public E removeFirst() throws EmptyDequeException {
		if (isEmpty())
		      throw new EmptyDequeException("Deque is empty.");;
		E temp=null;
		DLNode<E> e = front.getNext();
		if(e != null)
			e.setPrev(null);
		if(e ==null)
			rear = null;
		temp = front.getElement();
		front = e;
		size--;
	    return temp;
	}
	
	public E removeLast() throws EmptyDequeException {
		E temp=null;
		if (isEmpty())
		      throw new EmptyDequeException("Deque is empty.");
		DLNode<E> e = rear.getPrev();
		//System.out.println(e.getElement().toString());
		if(e !=null)
			e.setNext(null);
		if(e ==null)
			front = null;
		temp = rear.getElement();
		rear = e;
		size--;
		return temp;
	}

	public String toString(){
		DLNode<E> temp=front;
		String output="";
		if(size()==0)
			output=null;
		else if(size()==1)
			output = temp.getElement().toString();
		else{
		while(temp!= null){
			output +=temp.getElement()+"\t";
			temp=temp.getNext();
		}
		}
		return output;
	}
	
}
