import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int test_case = sc.nextInt();
		int[] result=new int[test_case];
		for (int tc = 0; tc < test_case; tc++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			result[tc]=A+B;
		}
		
		for(int i=0; i<result.length; i++) {
			System.out.println(result[i]);
		}
		
		
	}
}