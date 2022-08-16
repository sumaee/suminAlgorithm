import java.util.Scanner;

class Main
{   
	public static void main(String args[]) throws Exception
	{	
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int powerOfTwo = 1<<30;
		int ans = 0;
 
		while (powerOfTwo > 0 && K > 0) {
			powerOfTwo >>>= 1;
			if ((powerOfTwo & N) == 0) continue;
			N ^= powerOfTwo; 
			K--;
		}
		
		if (N == 0) {
			System.out.println(0);
			return;
		}
		
		System.out.println(powerOfTwo - N);
    }
}