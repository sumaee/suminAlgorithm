import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int K;
	static int maxLen;
	static int maxIdx;
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
			sb.append("#" + tc + " ");

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			visited = new boolean[N][N];
			maxIdx = Integer.MIN_VALUE;
			maxLen = Integer.MIN_VALUE;
			// 지도 채우기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxIdx = Math.max(arr[i][j], maxIdx);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == maxIdx) {
						dfs(i, j, 1, false);
					}
				}
			}
			sb.append(maxLen).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	public static void dfs(int row, int col, int len, boolean flag) {
		visited[row][col] = true;

		for (int k = 0; k < 4; k++) {
			int r = row + dr[k];
			int c = col + dc[k];

			if (r >= 0 && c >= 0 && r < N && c < N && !visited[r][c]) {

				// 그냥 가기
				if (arr[r][c] < arr[row][col]) {
					dfs(r, c, len + 1, flag);

				} else if (!flag) {
					// 깎으면서 가기
					for (int depth = 1; depth <= K; depth++) {
						if (arr[r][c] - depth < arr[row][col]) {
							int temp = arr[r][c];
							arr[r][c] -= depth;
							dfs(r, c, len + 1, true);
							arr[r][c] = temp;
						}
					}
				}

			}
		}

		maxLen = Math.max(len, maxLen);
		visited[row][col] = false;

	}
}
