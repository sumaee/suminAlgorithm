import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int INF = 987654321;
	static int test_case, n, m, t, s, g, h;
	static ArrayList<Node>[] graph;
	static int[] dis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			graph = new ArrayList[n + 1];

			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			// 그래프 초기 설정
			for (int i = 0; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}

			// 간선과 가중치 정보 받기
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());

				// 양뱡향이므로 양쪽으로 설정해주기
				graph[start].add(new Node(end, dist));
				graph[end].add(new Node(start, dist));
			}

			PriorityQueue<Integer> que = new PriorityQueue<>();

			int[] arr = new int[t];
			for (int i = 0; i < t; i++) {
				arr[i] = Integer.parseInt(br.readLine());
			}

			for (int a : arr) {
				long resultA = dijkstra(s, g) + dijkstra(g, h) + dijkstra(h, a);
				long resultB = dijkstra(s, h) + dijkstra(h, g) + dijkstra(g, a);
				long resultC = dijkstra(s, a);

				if (Math.min(resultA, resultB) == resultC) {
					que.offer(a);
				}
			}

			while (!que.isEmpty()) {
				sb.append(que.poll() + " ");
			}
			sb.append("\n");
		} // tc
		System.out.println(sb);
	}

	private static int dijkstra(int st, int ed) {
		boolean[] visited = new boolean[n + 1];
		dis = new int[n + 1];
		Arrays.fill(dis, INF);

		PriorityQueue<Node> que = new PriorityQueue<>();
		que.offer(new Node(st, 0));
		dis[st] = 0;
		while (!que.isEmpty()) {
			Node curr = que.poll();

			// 지금 노드가 이미 한번 방문되어서 처리된 적이 있다면 패스~
			if (visited[curr.idx])
				continue;

			visited[curr.idx] = true;
			// 지금과 연결돤 노드들 확인
			for (Node nxt : graph[curr.idx]) {
				if (dis[nxt.idx] > dis[curr.idx] + nxt.dist) {
					dis[nxt.idx] = dis[curr.idx] + nxt.dist;
					que.offer(new Node(nxt.idx, dis[nxt.idx]));
				}
			}
		}
		return dis[ed];
	}

	private static class Node implements Comparable<Node>{
		int idx, dist;

		public Node(int end, int dist) {
			this.dist = dist;
			this.idx = end;
		}

		@Override
		public int compareTo(Node o) {
			return dist-o.dist;
		}
	}
}
