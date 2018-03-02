package answers;

public class ArrayQueueTester {
	//test the code and visualization
		public static void main(String[] args){
			ArrayQueue<String> stack1 = new ArrayQueue<String>();
			System.out.println(stack1);
			stack1.enqueue("Ireland");
			System.out.println(stack1);
			stack1.dequeue();
			System.out.println(stack1);
			stack1.enqueue("England");
			System.out.println(stack1);
			stack1.dequeue();
			System.out.println(stack1);
			stack1.enqueue("Wales");
			System.out.println(stack1);
			stack1.dequeue();
			System.out.println(stack1);
			stack1.enqueue("Scotland");
			System.out.println(stack1);
			stack1.dequeue();
			System.out.println(stack1);
			stack1.enqueue("France");
			System.out.println(stack1);
			stack1.enqueue("Germany");
			System.out.println(stack1);
		}
}
