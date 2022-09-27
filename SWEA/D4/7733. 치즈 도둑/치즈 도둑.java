import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class a {
	int row;
	int col;

	public a(int row, int col) {
		this.col = col;
		this.row = row;
	}
}

public class Solution {
	static int N, minidx, max, count;
	static int[][] cheese;
	static boolean[][] visited;
	static Queue<a> que;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			N = Integer.parseInt(br.readLine());
			cheese = new int[N][N];
			visited = new boolean[N][N];
			que = new LinkedList<>();
			max = Integer.MIN_VALUE;
			minidx = Integer.MAX_VALUE;
			// 치즈 채우기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
					minidx = Math.min(minidx, cheese[i][j]);
				}
			}
			check();
		} // tc
		System.out.println(sb);
	}// main

	public static void check() {
		count = bfs();

		if (count == 0) {
			sb.append(max).append("\n");
			return;
		}

		max = Math.max(count, max);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cheese[i][j] == minidx) {
					cheese[i][j] = 0;
				}
			}
		}
		minidx++;
		check();
	}// bfs

	public static int bfs() {
		visited = new boolean[N][N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (cheese[i][j] != 0 && !visited[i][j]) {
					visited[i][j] = true;
					que.offer(new a(i, j));

					while (!que.isEmpty()) {
						a curr = que.poll();

						for (int k = 0; k < 4; k++) {
							int r = curr.row + dr[k];
							int c = curr.col + dc[k];

							if (r >= 0 && c >= 0 && r < N && c < N && cheese[r][c] != 0 && !visited[r][c]) {
								visited[r][c] = true;
								que.add(new a(r, c));
							}
						}
					}

					cnt++;
				}
			}
		}

		return cnt;
	}
}
