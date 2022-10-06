import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	static int V, E;
	static List<Integer>[] list;
	static int[] in_degree;
	static boolean[] visited;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken()); // 정점의 개수
			E = Integer.parseInt(st.nextToken()); // 간선의 개수

			in_degree = new int[V + 1];
			visited = new boolean[V + 1];
			list = new ArrayList[V + 1];
			stack = new Stack<>();
			for (int i = 0; i <= V; i++) {
				list[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				list[start].add(end);
				in_degree[end]++;
			}

			for (int i = 1; i <= V; i++) {
				if (in_degree[i] == 0) {
					Topological_sort(i);
				}
			}

			while (!stack.isEmpty()) {
				sb.append(stack.pop() + " ");
			}
			sb.append("\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void Topological_sort(int start) {
		visited[start] = true;

		for (int nxt : list[start]) {
			if (!visited[nxt]) {
				Topological_sort(nxt);
			}
		}
		stack.push(start);
	}

	private static class Node {
		int st, ed;

		public Node(int st, int ed) {
			this.st = st;
			this.ed = ed;
		}
	}// Node
}
