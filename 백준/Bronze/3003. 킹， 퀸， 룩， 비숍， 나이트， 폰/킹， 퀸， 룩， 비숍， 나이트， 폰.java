import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int[] white=new int[6];
		int[] real= {1, 1, 2, 2, 2, 8};
		int[] sum=new int[6]; 
		
		for(int i=0; i<white.length; i++) {
			white[i]=sc.nextInt();
		}
		
		for(int i=0; i<white.length;i++) {
			sum[i]=(real[i]-white[i]);
			System.out.print(sum[i]+" ");
		}
		
		
	}
}
