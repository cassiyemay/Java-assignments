package answers;
import java.util.Random;


public class C10 {
	public static void main(String[] args){
		Random generator = new Random();
		double duration=0;
		for(int N=1000000;N<=10000000;N+=1000000){
			//initialise data set
			System.out.print(N + ", ");
			int[] A= new int[N];
			for(int i=0; i< A.length; i++) 
				A[i]=generator.nextInt();
			int q=100;
		for(int i=0;i<5;i++){
			long start = System.currentTimeMillis();
			LinearSearch(A, A.length,q);
			long end = System.currentTimeMillis();
			duration += end-start;
			//System.out.println( duration);
			}
		duration = duration/5;
		System.out.println(duration);
		}
		
		}


	public static int LinearSearch(int[] A, int n, int q){
		int index = 0;
		while(index < n && A[index] != q)
			index +=1;
		
	if(index==n)
		return -1;
	else
		return index;	
	
}
	
}
