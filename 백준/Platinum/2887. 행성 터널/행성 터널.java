import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static locate[] star;
	static List<Node> list;
	static int[] p;
	static int N, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 행성의 개수
		star = new locate[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			star[i] = new locate(i, x, y, z);
		}

		p = new int[N];
		for (int i = 0; i < N; i++) {
			p[i] = i;
		}

		kruskal(0);
		System.out.println(result);
	}// main

	private static void kruskal(int start) {
		list = new ArrayList<>();

		// x좌표에 대해 정렬 후 리스트 추가
		Arrays.sort(star, new Comparator<locate>() {
			@Override
			public int compare(locate o1, locate o2) {
				return o1.x - o2.x;
			}
		});
		for (int i = 0; i < N - 1; i++) {
			int len = Math.abs(star[i].x - star[i + 1].x);
			list.add(new Node(star[i].idx, star[i + 1].idx, len));
		}

		// y좌표에 대해 정렬 후 리스트 추가
		Arrays.sort(star, new Comparator<locate>() {
			@Override
			public int compare(locate o1, locate o2) {
				return o1.y - o2.y;
			}
		});
		for (int i = 0; i < N - 1; i++) {
			int len = Math.abs(star[i].y - star[i + 1].y);
			list.add(new Node(star[i].idx, star[i + 1].idx, len));
		}

		// z좌표에 대해 정렬 후 리스트 추가
		Arrays.sort(star, new Comparator<locate>() {
			@Override
			public int compare(locate o1, locate o2) {
				return o1.z - o2.z;
			}
		});
		for (int i = 0; i < N - 1; i++) {
			int len = Math.abs(star[i].z - star[i + 1].z);
			list.add(new Node(star[i].idx, star[i + 1].idx, len));
		}

		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.len - o2.len;
			}
		});

		result = 0;
		for (int i = 0; i < list.size(); i++) {
			Node star = list.get(i);

			if (find(star.st) == find(star.ed)) {
				continue;
			}

			result += star.len;
			union(star.st, star.ed);
		}
	}

	private static void union(int x, int y) {
		p[find(y)] = find(x);
	}

	private static int find(int x) {
		if (x != p[x]) {
			p[x] = find(p[x]);
		}
		return p[x];
	}

	private static class locate {
		int x, y, z, idx;

		public locate(int idx, int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.idx = idx;
		}
	}

	private static class Node {
		int st, ed, len;

		public Node(int st, int ed, int len) {
			this.st = st;
			this.ed = ed;
			this.len = len;
		}
	}
}
