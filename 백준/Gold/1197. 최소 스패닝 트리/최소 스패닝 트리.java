import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V, E, result;
	static boolean[] visited;
	static List<Node>[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		map = new ArrayList[V + 1];
		visited = new boolean[V + 1];
		result = 0;
		for (int i = 1; i <= V; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int stIdx = Integer.parseInt(st.nextToken());
			int edIdx = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());

			map[stIdx].add(new Node(edIdx, size));
			map[edIdx].add(new Node(stIdx, size));
		}
		prim(1);
		System.out.println(result);
	}// main

	private static void prim(int start) {
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.size - o2.size;
			}

		});

		que.offer(new Node(start, 0));
		while (!que.isEmpty()) {
			Node curr = que.poll();

			if (visited[curr.idx]) {
				continue;
			}

			visited[curr.idx] = true;
			result += curr.size;

			for (Node nxt : map[curr.idx]) {
				if (!visited[nxt.idx]) {
					que.offer(new Node(nxt.idx, nxt.size));
				}
			}
		}
	}// prim

	private static class Node {
		int idx, size;

		public Node(int ed, int size) {
			this.idx = ed;
			this.size = size;
		}

	}

}
