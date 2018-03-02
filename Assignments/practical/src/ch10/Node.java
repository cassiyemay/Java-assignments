package ch10;

public class Node {
	private String element;
	private Node next;
	
	public Node(String s, Node n){
		element = s;
		next = n;
	}
	
	public String getElement(){
		return element;
	}
	
	public Node getNext(){
		return next;
	}
	public void setElement(String newElem){
		element = newElem;
	}
	public void setNext(Node newNext){
		next = newNext;
	}
	
}
