import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int M, N, K;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken()); // col
			N = Integer.parseInt(st.nextToken()); // row
			K = Integer.parseInt(st.nextToken());

			visited = new boolean[N][M];
			arr = new int[N][M];
			int cnt = 0;

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				arr[row][col] = 1;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && arr[i][j] == 1) {
						cnt++;
						dfs(i, j);
					}
				}
			}

			sb.append(cnt + "\n");
		} // tc
		System.out.println(sb);
	}// main

	private static void dfs(int r, int c) {
		visited[r][c] = true;

		for (int i = 0; i < 4; i++) {
			int row = r + dr[i];
			int col = c + dc[i];

			if (row >= 0 && col >= 0 && row < N && col < M && arr[row][col] == 1 && !visited[row][col]) {
				dfs(row, col);
			}
		}
	}
}
