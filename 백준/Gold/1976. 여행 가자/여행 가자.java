import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static int[] check;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		check = new int[M];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int conn = Integer.parseInt(st.nextToken());
				if (conn == 1) {
					union(i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			check[i] = Integer.parseInt(st.nextToken());
		}

		if (isPossible(check[0])) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}// main

	private static boolean isPossible(int a) {

		for (int i = 1; i < M; i++) {
			if (find(a) != find(check[i])) {
				return false;
			}
		}

		return true;
	}//isPossible

	public static void union(int x, int y) {
		parent[find(y)] = find(x);
	}// union

	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}// find
}
