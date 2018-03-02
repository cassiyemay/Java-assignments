package answers;

public class LinkedStack {
	
	// inner class 
	public class Node {
		Object element;
		Node next;
		
		public Node(Object element) {
			this.element = element;
			
		}
		
		public Object getElement(){
			return element;
		}
		
		public Node getNext(){
			return next;
		}
		 
		public void setElement(Object NewElem){
			element = NewElem;
		}
		public void setNext(Node NewNext){
			next = NewNext;
		}
		public String toString(){
			return element.toString();
		}
	}
		// instances for class LinkedStack
		protected int size;
		protected Node top;
		
		// one constructor
		public LinkedStack(){
			top = null;
			size = 0;
		}
		
		// 5 methods for stack
		public int size(){
			return size;
		}
		
		public boolean isEmpty(){
			if (top == null) return true;
	        return false;
		}
		
		public void push(Object o){
			Node node = new Node(o);
			node.next = top;
			top = node;
			size ++;
		}
		
		public Object pop() throws EmptyStackException{
			if(isEmpty()) throw new EmptyStackException ("stack is empty");
			Object e = top.element;
			top.element = null;
			top = top.next;
			size--;
			return e;
		}
		
		public Object top() throws EmptyStackException{
			if(isEmpty()) throw new EmptyStackException("stack is empty");
			return top.element;
		}
		
		//state visualizaiton
		public String toString(){
			String output = "";
			Node node = top;
			while(node !=null){
				output = node.element.toString() + " " + output;
				node = node.next;
			}
			
		return "" + size + "\t" + output;
			
		}
		
		//test class
		public static void main(String[] args){
			LinkedStack s = new LinkedStack();
			System.out.println(s);
			s.push("A");
			System.out.println(s);
			s.push("B");
			System.out.println(s);
			s.push("C");
			System.out.println(s);
		}
		
	}

