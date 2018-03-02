package answers;

public class ArrayQueue<E> implements Queue<E> {
	private E Q[];
	private int N;
	private int front=0, rear=0;
	public static final int CAPACITY = 1000;
	
	public ArrayQueue(){
		this(CAPACITY);
		Q = (E[]) new Object[N];
	}
	
	public ArrayQueue(int capacity){
		N = capacity;
		Q = (E[])new Object[N];
	}
	
	public int size(){
		return (N + rear - front) % N;
	}
	
	public boolean isEmpty(){
		return front==rear;
	}
	
	public void enqueue(E element){
		if((rear+1) % N == front) {
			throw new IllegalStateException("Queue is full");
		}
		Q[rear] = element;
		rear = (rear+1) % N;
	}
	
	public E dequeue() throws EmptyQueueException{
		if(size()==0) throw new EmptyQueueException("Queue is empty");
		E e= Q[front];
		Q[front] = null;
		front = (front+1) % N;
		return e;
	}
	
	public E front() throws EmptyQueueException{
		if(size()==0) throw new EmptyQueueException("Queue is empty");
		else
			return Q[front];
	}
	
	public String toString(){
		String s="";
		for(int i =front; (i < rear); i++)
			s +=  Q[i] + "\t";
		return "front is: "+ front + "   rear is: "+rear+ "  "+ s ;
	}
	
}


