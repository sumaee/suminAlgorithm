import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static double value, cost;
	static long[] length;
	static boolean[] visited;
	static int[][] island;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine()); // 섬 개수

			island = new int[N][2];

			// x축받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				island[i][0] = x;
			}
			// y축받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int y = Integer.parseInt(st.nextToken());
				island[i][1] = y;
			}

			// 세율
			value = Double.parseDouble(br.readLine());
			cost = 0; // 지불해야하는 비용

			visited = new boolean[N];
			length = new long[N];

			// 제일 먼저 길이를 맥스로 채우기
			Arrays.fill(length, Long.MAX_VALUE);
			prim(0);
		} // tc
		System.out.println(sb);
	}// main

	private static void prim(int idx) {
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return Long.compare(o1.len, o2.len);
			}

		});
		que.offer(new Node(idx, 0));

		while (!que.isEmpty()) {
			Node curr = que.poll();

			// 만약 방문했다면 계속
			if (visited[curr.idx]) {
				continue;
			}

			// 방문을 안한 거라면 방문처리
			visited[curr.idx] = true;
			length[curr.idx] = curr.len;
			cost += length[curr.idx];

			// 맨 처음 섬에서 연결할수 있는 섬들의 거리를 각각 구해서 저장해놓음
			// 그리고 나서 우선순위 큐로 인해 거리가 제일 짧은 애와 연결이 되고
			// 그 애와 다른 섬들의 거리를 또 비교해 거리가 가장 짧은 것 구해서 큐에 넣음
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					// 방문 안한 것과 현재 섬의 거리 구하기
					long len = (long) (Math.pow(Math.abs(island[i][0] - island[curr.idx][0]), 2)
							+ Math.pow(Math.abs(island[i][1] - island[curr.idx][1]), 2));
					if (length[i] > len) {
						length[i] = len;
						que.offer(new Node(i, len));
					}
				}
			}
		}

		// 연결이 다 끝나고 세율 계산
		sb.append(Math.round(cost * value)).append("\n");
	}

	private static class Node {
		int idx;
		long len;

		public Node(int idx, long len) {
			this.idx = idx;
			this.len = len;
		}
	}
}