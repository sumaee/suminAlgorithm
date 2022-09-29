import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static List<Integer>[] people;
	static int[] result;
	static int start, N, max, pe;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = 10;
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			// 인접리스트 초기화
			people = new ArrayList[101];
			visited = new boolean[101];
			result = new int[101];
			for (int i = 0; i <= 100; i++) {
				people[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			// 데이터길이
			N = Integer.parseInt(st.nextToken());
			// 연락의 시작
			start = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				people[from].add(to);
			}

			bfs();
			sb.append(pe).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		que.add(start);
		visited[start] = true;

		while (!que.isEmpty()) {
			int curr = que.poll();

			for (int next : people[curr]) {
				if (!visited[next]) {

					visited[next] = true;
					que.offer(next);
					result[next] = result[curr] + 1;
				}
			}

			max = result[curr];
		}

		for (int i = 1; i <= 100; i++) {
			if (result[i] == max) {
				pe = i;
			}
		}

	}// bfs
}
