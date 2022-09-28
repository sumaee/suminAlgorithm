import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int F;
	static int[] p, rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			Map<String, Integer> list = new HashMap<>();
			F = Integer.parseInt(br.readLine());

			// 3줄이 나온다고 하면 각각 다른 사람 2명씩 총 6명이 나올 수 있기때문에
			// 최대 *2 만큼의 크기를 설정
			p = new int[F * 2];
			rank = new int[F * 2];

			// 초기 설정
			for (int i = 0; i < F * 2; i++) {
				p[i] = i;
				rank[i] = 1;
			}

			int idx = 0;
			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());

				String x = st.nextToken();
				String y = st.nextToken();

				if (!list.containsKey(x)) {
					list.put(x, idx++);
				}

				if (!list.containsKey(y)) {
					list.put(y, idx++);
				}

				union(list.get(x), list.get(y));
				sb.append(rank[find(list.get(x))]).append("\n");
			}

		} // tc
		System.out.println(sb);
	}// main

	private static void union(int x, int y) {
		int a = find(x);
		int b = find(y);

		if(a!=b) {
			p[b] = a;
			rank[a] += rank[b];
			rank[b] = rank[a];
		}
	}

	private static int find(int x) {
		if (x != p[x])
			p[x] = find(p[x]);
		return p[x];
	}
}
