package answers;

public class ArrayStack {
	protected int capacity;
	protected int top =-1;
	protected Object S[];
	public static final int CAPACITY = 1000;
	
	//two constructors
	public ArrayStack(){
		this(CAPACITY);
		S = (Object[]) new Object[capacity];
	}
	
	public ArrayStack(int cap){
		capacity = cap;
		S = (Object[]) new Object[capacity];
	}
	
	// 5 methods for stack
	public int size(){
		return top +1;
	}
	public boolean isEmpty(){
		return (top < 0);
	}
	
	public void push(Object element) throws FullStackException{
		if(size() == capacity) throw new FullStackException("stack is full");
		S[++top] = element;
		
	}
	
	public Object pop() throws EmptyStackException {
		Object element;
		if(isEmpty()) throw new EmptyStackException("Stack is empty");
		element = S[top];
		S[top--] = null;
		return element;
	}
	
	public Object top() throws EmptyStackException {
		if(isEmpty()) throw new EmptyStackException("Stack is empty");
		else
			return S[top];
	}
	// state visualization
	public String toString(){
		String output;
		output = "";
		if(size()== 0)
			output +=  " " +"\t";
		if(size() >=1)
			for(int i =0; i<size(); i++){
				output += S[i] +" ";
		}
		return size() + "\t"+ output;	
	}
	
	// test stack methods
	public static void main(String[] args){
		ArrayStack s = new ArrayStack();
		System.out.println(s);
		s.push("A");
		System.out.println(s);
		s.push("B");
		System.out.println(s);
		s.push("C");
		System.out.println(s);
	}
	
}


