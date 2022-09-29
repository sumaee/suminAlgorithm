import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
	static int N;
	static int[][] map;
	static int[][] temp;
	static boolean[][] visited;
	static PriorityQueue<Node> que;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(br.readLine()); // 지도 크기
			map = new int[N][N];
			temp = new int[N][N];
			visited = new boolean[N][N];

			// 큐를 우선순위 큐로 만들기
			// 값이 가장 작은 값을 먼저 선택하기 위함
			que = new PriorityQueue<>(new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					return o1.value - o2.value;
				}

			});

			// 지도 입력
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			// 누적합을 쌓을 temp의 모든 값을 맥스로 입력
			for (int i = 0; i < N; i++) {
				Arrays.fill(temp[i], Integer.MAX_VALUE);
			}
			// 시작점 0으로 초기화
			temp[0][0] = 0;
			que.offer(new Node(0, 0, 0));

			dijkstra(0);
			int result = temp[N - 1][N - 1];
			sb.append(result).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void dijkstra(int st) {

		while (!que.isEmpty()) {
			Node curr = que.poll();

			// 이미 방문한 곳이라면 다음 진행
			if (visited[curr.row][curr.col])
				continue;

			// 아니라면 방문처리후 4방을 확인하여 필요한 작업 수행
			visited[curr.row][curr.col] = true;
			
			// 4방을 돌면서 temp에 있는 값과 비교해서 더 작은 값을 temp에 갱신
			for (int i = 0; i < 4; i++) {
				int r = curr.row + dr[i];
				int c = curr.col + dc[i];

				// 경계 안에 있고 방문하지 않았다면
				if (r >= 0 && c >= 0 && r < N && c < N && !visited[r][c]) {
					if (temp[r][c] > curr.value + map[r][c]) {
						temp[r][c] = curr.value + map[r][c];
						que.offer(new Node(r, c, temp[r][c]));

					}
				}
			}
		}

	}// dijkstra

	private static class Node {
		int row, col, value;

		public Node(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}

	}
}
