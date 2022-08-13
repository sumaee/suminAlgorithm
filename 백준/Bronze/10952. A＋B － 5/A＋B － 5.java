import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = 1;
		while (true) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			if(A==0&&B==0) {
				return;
			}
			
			System.out.println(A + B);
		}
	}
}
