import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());

		long answer = (multiply(A, B, C));
		System.out.println(answer % C);
	}// main

	public static long multiply(long a, long b, long c) {

		// 기저 조건
		if (b == 1) {
			return a;
		}

		// 유도 조건
		long result;

		// 짝수일 때
		if (b % 2 == 0) {
			result = multiply(a, b / 2, c);
			return (result * result) % c;
		}

		// 홀수일 때
		else {
			result = multiply(a, (b - 1) / 2, c);
			return ((result * result) % c * a) % c;
		}

	}
}
