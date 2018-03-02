package answers;

public class B6 {
	public static void main(String[] args){
		int[] A = {5,2,3,4,1};
		int n=A.length;
		minValueIndex(A,n);
		
		
	}
	
	public static int minValueIndex(int[] A, int n){
		System.out.println("k" + " " + " A[minValueIndex]" + " A[K]"+ " minValueIndex");
		int minValueIndex =0;
		for(int k=1; k<n; k++){
			System.out.print(k + ": ");
			System.out.print(" "+A[minValueIndex] + "                ");
			System.out.print(A[k]+ "       ");
			if(A[minValueIndex] > A[k])
				minValueIndex=k;
			System.out.println(minValueIndex + " ");
		}
		System.out.println();
		System.out.println("Output: "+ minValueIndex);
		return minValueIndex;
	}

}
