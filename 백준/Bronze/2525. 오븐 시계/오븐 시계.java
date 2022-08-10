import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		int time=60*A+B;
		time+=C;
		
		int H=(time/60)%24;
		int M=(time%60);
		
		System.out.println(H+" "+M);
	}
}