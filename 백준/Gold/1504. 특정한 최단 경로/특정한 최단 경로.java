import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, E, start;
	static List<Node>[] graph;
	static boolean[] visited;
	static int[] sizeSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		sizeSum = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int stIdx = Integer.parseInt(st.nextToken());
			int edIdx = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());

			graph[stIdx].add(new Node(edIdx, size));
			graph[edIdx].add(new Node(stIdx, size));
		}

		st = new StringTokenizer(br.readLine());
		int must1 = Integer.parseInt(st.nextToken());
		int must2 = Integer.parseInt(st.nextToken());

		long result1 = 0;
		result1 += dijkstra(1, must1);
		result1 += dijkstra(must1, must2);
		result1 += dijkstra(must2, N);

		long result2 = 0;
		result2 += dijkstra(1, must2);
		result2 += dijkstra(must2, must1);
		result2 += dijkstra(must1, N);

		long answer = Math.min(result1, result2);

		answer = answer >= Integer.MAX_VALUE ? -1 : answer;
		System.out.println(answer);

	}

	private static int dijkstra(int start, int end) {
		Arrays.fill(sizeSum, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		boolean check = false;
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.size - o2.size;
			}
		});

		que.offer(new Node(start, 0));
		sizeSum[start] = 0;

		while (!que.isEmpty()) {
			Node curr = que.poll();

			if (!visited[curr.idx]) {
				visited[curr.idx] = true;

				for (Node nxt : graph[curr.idx]) {
					if (!visited[nxt.idx] && sizeSum[nxt.idx] > nxt.size + sizeSum[curr.idx]) {
						sizeSum[nxt.idx] = nxt.size + sizeSum[curr.idx];
						que.offer(new Node(nxt.idx, sizeSum[nxt.idx]));
					}
				}
			}

		}
		return sizeSum[end];
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