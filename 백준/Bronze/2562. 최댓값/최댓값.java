import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int[] arr=new int[9];
		
		int max=Integer.MIN_VALUE;
		int idx=0;
		for(int i=0; i<9; i++) {
			arr[i]=sc.nextInt();
			if(arr[i]>max) {
				idx=i;
				max=arr[i];
			}
		}
		
		System.out.println(arr[idx]);
		System.out.println(idx+1);
		
		
		
	}
}
