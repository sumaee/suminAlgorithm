import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int V;
	static boolean[] visited;
	static int[][] graph;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		V = Integer.parseInt(st.nextToken()); // 탐색 시작 정점 번호

		visited = new boolean[N + 1];
		graph = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 연결되 있는 것끼리 배열에 넣기
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		dfs(V);
		sb.append("\n");
		Arrays.fill(visited, false);
		bfs(V);

		System.out.println(sb);
	}// main

	public static void dfs(int idx) {
		visited[idx] = true;
		sb.append(idx + " ");
		for (int i = 1; i <= N; i++) {
			if (graph[idx][i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}

	public static void bfs(int idx) {
		visited[idx] = true;
		Queue<Integer> que = new LinkedList<>();
		que.offer(idx);
		while (!que.isEmpty()) {
			int curr = que.poll();
			sb.append(curr + " ");
			for (int i = 1; i <= N; i++) {
				if (graph[curr][i] == 1 && !visited[i]) {
					visited[i] = true;
					que.offer(i);
				}
			}
		}
	}
}
