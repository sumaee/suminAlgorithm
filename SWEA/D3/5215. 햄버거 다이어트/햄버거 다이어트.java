import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, L;
	static int[] taste;
	static int[] K;
	static boolean[] check;// 골랐는지 아닌지 확인
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			taste = new int[N];
			K = new int[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				taste[i] = Integer.parseInt(st.nextToken());
				K[i] = Integer.parseInt(st.nextToken());
			}
			check = new boolean[N];
			powerSet(0);
			sb.append(ans).append("\n");
			ans = 0;

		} // tc
		System.out.println(sb);
	}// main

	static void powerSet(int idx) {
		// 재료의 갯수인 N까지 idx가 왔다면 모든 재료를 구한것
		if (idx == N) {
			// 고른 재료들의 맛의 합과 칼로리의 합을 수함
			// 더한 칼로리의 합이 K가 넘지 않는다면
			// 현재 맛 점수와 ans 중 큰값을 구함
			int tSum = 0, kSum = 0;
			for (int i = 0; i < N; i++) {
				// i번째 재료
				if (check[i]) {
					tSum += taste[i];
					kSum += K[i];
				}
			}
			if (kSum <= L) {
				ans = Math.max(ans, tSum);
			}
			return;
		}
		check[idx] = true; // 재료 고름
		powerSet(idx + 1);
		check[idx] = false; // 재료 안고름
		powerSet(idx + 1);
	}
}
