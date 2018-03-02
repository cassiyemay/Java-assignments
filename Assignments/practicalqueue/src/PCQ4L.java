

public class PCQ4L {
	public static void main(String[] args){
		//LinkedDeque<String> a = new LinkedDeque<String>();
		NodeDeque<String> a = new NodeDeque<String>();
		System.out.println(a);
		a.addFirst("Ireland");
		System.out.println(a);
		a.removeLast();
		System.out.println(a);
		a.addLast("England");
		System.out.println(a);
		a.removeFirst();
		System.out.println(a);
		a.addLast("Wales");
		System.out.println(a);
		a.addFirst("Scotland");
		System.out.println(a);
		a.addLast("France");
		System.out.println(a);
		a.removeFirst();
		System.out.println(a);
		a.removeLast();
		System.out.println(a);
		a.addLast("Germany");
		System.out.println(a);
		
	
	

}
}
