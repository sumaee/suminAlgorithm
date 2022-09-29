import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 사람 수
			M = Integer.parseInt(st.nextToken()); // 관계 수

			p = new int[N + 1]; // 1부터 시작되기 때문에
			// p초기화
			for (int i = 1; i <= N; i++) {
				p[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				union(x, y);
			}

			List<Integer> list = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				p[i] = find(i);
				if (!list.contains(p[i])) {
					list.add(p[i]);
				}
			}
			sb.append(list.size()).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void union(int x, int y) {
		p[find(y)] = find(x);
	}// union

	private static int find(int x) {
		if (x != p[x])
			p[x] = find(p[x]);
		return p[x];
	}// find
}
