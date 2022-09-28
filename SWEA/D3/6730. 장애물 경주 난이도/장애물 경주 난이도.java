import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, up, down;
	static int[] block;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine()); // 블록 개수
			block = new int[N];
			up = Integer.MIN_VALUE; // 올라갈 때 최고 변화
			down = Integer.MIN_VALUE; // 내렬갈 때 최고 변화

			// 블록을 먼저 채워주기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				block[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < N - 1; i++) {
				check(block[i], block[i + 1]);
			}
			
			if (up == Integer.MIN_VALUE) {
				up = 0;
			}
			
			if (down == Integer.MIN_VALUE) {
				down = 0;
			}
			sb.append(up + " " + down).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void check(int a, int b) {

		// 내려가는 것
		if (a > b) {
			down = Math.max(down, a - b);
		} else { // 올라가는 것
			up = Math.max(up, b - a);
		}
	}
}
