import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		int m = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (a == 0) {
				union(x, y);
			} else {
				if (check(x, y)) {
					sb.append("YES").append("\n");
				} else {
					sb.append("NO").append("\n");
				}
			}
		}
		
		System.out.println(sb);
	}// main

	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		} else {
			return parent[x]=find(parent[x]);
		}
	}// find

	public static void union(int x, int y) {
		
			parent[find(y)] = find(x);
		
	}// union

	public static boolean check(int x, int y) {

		return find(x) == find(y);
	}
}
