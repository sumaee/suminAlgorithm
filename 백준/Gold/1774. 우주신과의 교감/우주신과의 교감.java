import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] p;
	static Node[] god;
	static List<conn> list;
	static double result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 우주신 개수
		M = Integer.parseInt(st.nextToken()); // 연결된 통로 개수

		// 초기 설정
		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		god = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			god[i] = new Node(i, x, y);
		}

		// 연결된 통로 정리
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			union(start, end);
		}

		kruskal();
		System.out.printf("%.2f", result);
	}// main

	private static void kruskal() {
		// 모든 통로들을 간선과 길이를 저장
		list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				double size = Math.sqrt(Math.pow(god[i].x - god[j].x, 2) + Math.pow(god[i].y - god[j].y, 2));
				list.add(new conn(god[i].idx, god[j].idx, size));
			}
		}

		// 길이를 기준으로 정렬
		Collections.sort(list, new Comparator<conn>() {
			@Override
			public int compare(conn o1, conn o2) {
				return Double.compare(o1.size, o2.size);
			}
		});

		result = 0;
		for (int i = 0; i < list.size(); i++) {
			conn curr = list.get(i);
			// 부모가 같지 않다는 것은 연결이 안되있다는 뜻
			if (find(curr.start) != find(curr.end)) {
				result += curr.size;
				// 부모 연결해주기
				union(curr.start, curr.end);
			}
		}
	}

	private static int find(int x) {
		if (x != p[x]) {
			p[x] = find(p[x]);
		}
		return p[x];
	}

	private static void union(int x, int y) {
		p[find(y)] = find(x);
	}

	private static class conn {
		int start, end;
		double size;

		public conn(int start, int end, double size) {
			this.start = start;
			this.end = end;
			this.size = size;
		}
	}

	private static class Node {
		int x, y, idx;

		public Node(int idx, int x, int y) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}
}
