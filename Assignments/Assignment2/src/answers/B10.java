package answers;

public class B10 {
	public static void main(String[] args){
		int[] A = {5,2,3,4,1};
		int n=A.length;
		int q=9;
		LinearSearch(A,n,q);
		
	}
	
	public static int LinearSearch(int[] A, int n, int q){
		System.out.println("index" + "        A[index]");
		int index = 0;
		System.out.print(index+" ini_value      ");
		System.out.print(A[index]);
		System.out.println();
		while(index < n && A[index] != q){
			index +=1;
		System.out.print(index+"                ");
		System.out.print(A[index]);
		System.out.println();
		}
		System.out.println();
	if(index==n){
		System.out.println("Output: -1" );
		return -1;}
	else{
		System.out.println("Output: " + index);
		return index;}
	
	
}
	
	
}
