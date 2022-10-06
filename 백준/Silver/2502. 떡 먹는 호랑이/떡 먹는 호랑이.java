import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int D, K;
	static int[] dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken()); // 할머니가 넘어온날
		K = Integer.parseInt(st.nextToken()); // 떡의 개수
		dp = new int[40];
		fibo();
		System.out.println(sb);
	}// main

	private static void fibo() {

		dp[0] = 0;
		int A = 1;
		int B = 1;
		while (true) {

			B = A;

			while (B < K) {

				dp[1] = A;
				dp[2] = B;
				for (int day = 3; day <= D; day++) {
					dp[day] = dp[day - 1] + dp[day - 2];
				}

				if (dp[D] == K) {
					sb.append(A + "\n" + B);
					return;
				}

				B++;
			}
			A++;
		}
	}
}
