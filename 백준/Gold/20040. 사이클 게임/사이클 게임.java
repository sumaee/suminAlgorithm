import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] parent;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 정점의 개수
		m = Integer.parseInt(st.nextToken()); // 진행된 차례수
		cnt = 0;
		parent = new int[n];
		// 초기 연결 설정
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		// 연결하기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			union(x, y, i);
		}
		System.out.println(cnt);
	}// main

	private static int find(int x) {
		if (x != parent[x])
			parent[x] = find(parent[x]);
		return parent[x];
	}// find

	private static void union(int x, int y, int i) {

		// 만약 합치려고 할 때 부모가 같으면 이미 연결되어 있다는 것이므로
		// 사이클 존재 해당 수를 합칠 때의 i+1번째가 답이 됨
		if (find(y) == find(x) && cnt == 0) {
			cnt = i + 1;
		}
		parent[find(y)] = find(x);
	}// union
}
