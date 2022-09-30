import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, K, result;
	static int[] time = new int[100001];
	static boolean[] visited = new boolean[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(result);
	}

	// main

	private static void bfs() {
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.cnt - o2.cnt;
			}

		});

		que.offer(new Node(N, 0));

		while (!que.isEmpty()) {
			Node curr = que.poll();

			visited[curr.idx] = true;
			
			if (curr.idx == K) {
				result = curr.cnt;
                return;
			}

			// 0초 순간이동
			if (curr.idx * 2 <= 100000 && !visited[curr.idx * 2]) {
				que.offer(new Node(curr.idx * 2, curr.cnt));
			}

			// 1초 우로 이동
			if (curr.idx + 1 <= 100000 && !visited[curr.idx + 1]) {
				que.offer(new Node(curr.idx +1, curr.cnt + 1));
			}

			// 1초 좌로이동
			if (curr.idx - 1 >= 0 && !visited[curr.idx - 1]) {
				que.offer(new Node(curr.idx -1, curr.cnt + 1));
			}
		} // bfs

	}

	private static class Node {
		int idx, cnt;

		public Node(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}
}
