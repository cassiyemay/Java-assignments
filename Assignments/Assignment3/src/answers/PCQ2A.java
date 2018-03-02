package answers;

public class PCQ2A {
	public static void main(String[] args){
		ArrayStack a = new ArrayStack();
		System.out.println(a);
		a.push(new Character('e'));
		System.out.println(a);
		a.push(new Character('s'));
		System.out.println(a);
		a.push(new Character('c'));
		System.out.println(a);
		a.pop();
		System.out.println(a);
		a.push(new Character('u'));
		System.out.println(a);
		a.push(new Character('a'));
		System.out.println(a);
		a.pop();
		System.out.println(a);
		a.push(new Character('o'));
		System.out.println(a);
		a.push(new Character('t'));
		System.out.println(a);
		a.pop();
		System.out.println(a);
		a.push(new Character('h'));
		System.out.println(a);
	}

}
