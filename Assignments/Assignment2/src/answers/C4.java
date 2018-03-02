package answers;

public class C4 {
	public static void main(String[] args){
		double duration=0;
		for(int i=0;i<5;i++){
			long start = System.currentTimeMillis();
			Difference(3,8);
			long end = System.currentTimeMillis();
			duration += end-start;
			}
		duration = duration/5;
		System.out.println("Running Time= " + duration);
		}
	
	public static int Difference(int a,int b){
		if(a>b)
			return (a-b);
		else
			return (b-a);
	}

}
