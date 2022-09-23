import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int result;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static List<Integer> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine()); // 지도 크기
		arr = new int[N][N];
		visited = new boolean[N][N];
		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String a = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = a.charAt(j) - '0';
			}
		}
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					result = 1;
					cnt++;
					dfs(i, j);
					list.add(result);
					Collections.sort(list);
				}
			}
		}
		for (int i : list) {
			sb.append(i).append("\n");
		}
		System.out.println(cnt);
		System.out.println(sb);
	}// main

	public static void dfs(int row, int col) {
		visited[row][col] = true;
		for (int i = 0; i < 4; i++) {
			int r = row + dr[i];
			int c = col + dc[i];

			if (r >= 0 && c >= 0 && r < N && c < N && arr[r][c] == 1 && !visited[r][c]) {
				result++;
				dfs(r, c);
			}
		}
	}// dfs
}
