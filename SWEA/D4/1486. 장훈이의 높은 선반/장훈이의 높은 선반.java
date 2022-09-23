import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int B;
	static int[] arr;
	static boolean[] visit;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 점원의 수
			B = Integer.parseInt(st.nextToken()); // 높이 제한

			arr = new int[N]; // 직원들의 키를 담을 배열
			visit = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			min = Integer.MAX_VALUE;
			check(0);
			sb.append(min).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	public static void check(int idx) {
		if (idx == N) {
			int sum = 0;

			for (int i = 0; i < N; i++) {
				if (visit[i]) {
					sum += arr[i];
				}
			}

			if (sum >= B && (sum - B) < min) {
				min = sum - B;
			}
			return;
		}

		visit[idx] = true;
		check(idx + 1);
		visit[idx] = false;
		check(idx + 1);
	}
}
