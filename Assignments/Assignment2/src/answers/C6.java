package answers;
import java.util.Random;

public class C6 {
	public static void main(String[] args){
		Random generator = new Random();
		double duration=0;
		for(int N=1000000;N<=10000000;N+=1000000){
			//initialise data set
			System.out.print(N + ",");
			int[] A= new int[N];
			for(int i=0; i< A.length; i++) 
				A[i]=generator.nextInt();
			
		for(int i=0;i<5;i++){
			long start = System.currentTimeMillis();
			minValueIndex(A,A.length);
			long end = System.currentTimeMillis();
			duration += end-start;
			}
		duration = duration/5;
		System.out.print(duration);
		System.out.println();
		}
		
		}
		
	public static int minValueIndex(int[] A, int n){
		int minValueIndex =0;
		for(int k=1; k<n; k++){
			if(A[minValueIndex] > A[k])
				minValueIndex=k;
		}
		return minValueIndex;
	}

}
