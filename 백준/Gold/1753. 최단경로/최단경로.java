import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, E, start;
	static List<Node>[] graph;
	static boolean[] visited;
	static int[] sizeSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken()); // 정점의 개수
		E = Integer.parseInt(st.nextToken()); // 간선의 개수
		start = Integer.parseInt(br.readLine());// 시작점
		graph = new ArrayList[V + 1];
		visited = new boolean[V + 1];
		sizeSum = new int[V + 1];

		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
			sizeSum[i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int stIdx = Integer.parseInt(st.nextToken());
			int edIdx = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());

			graph[stIdx].add(new Node(edIdx, size));
		}

		dijkstra(start);

		for (int i = 1; i <= V; i++) {

			System.out.println(sizeSum[i] == Integer.MAX_VALUE ? "INF" : sizeSum[i]);
		}
	}// main

	private static void dijkstra(int start) {
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.size - o2.size;
			}

		});
		que.add(new Node(start, 0));
		sizeSum[start] = 0;

		while (!que.isEmpty()) {
			Node curr = que.poll();

			if (visited[curr.idx])
				continue;

			visited[curr.idx] = true;

			for (Node next : graph[curr.idx]) {
				if (!visited[next.idx]) {

					if (sizeSum[next.idx] > sizeSum[curr.idx] + next.size) {
						sizeSum[next.idx] = sizeSum[curr.idx] + next.size;
						que.add(new Node(next.idx, sizeSum[next.idx]));
					}
				}
			}
		}

	}

	private static class Node {
		int idx;
		int size;

		public Node(int idx, int size) {
			this.idx = idx;
			this.size = size;
		}
	}
}
