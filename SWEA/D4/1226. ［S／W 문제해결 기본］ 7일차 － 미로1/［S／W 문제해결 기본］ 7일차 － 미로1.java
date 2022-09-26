import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class location {
	int row;
	int col;

	public location(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class Solution {
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static Queue<location> que;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int t = Integer.parseInt(br.readLine());
			sb.append("#" + t + " ");
			
			visited = new boolean[16][16];
			arr = new int[16][16];
			que = new LinkedList<>();
			
			// 미로 입력받기
			for (int i = 0; i < 16; i++) {
				String str = br.readLine();
				for (int j = 0; j < 16; j++) {
					arr[i][j] = str.charAt(j) - '0';
					if (arr[i][j] == 1) {
						visited[i][j] = true;
					} else if (arr[i][j] == 2) {
						visited[i][j] = true;
						que.offer(new location(i, j));
					}
				}
			}

			bfs();

		} // tc
		System.out.println(sb);
	}// main

	public static void bfs() {

		while (!que.isEmpty()) {
			location curr = que.poll();
			
			for (int i = 0; i < 4; i++) {
				int r = curr.row + dr[i];
				int c = curr.col + dc[i];

				// 범위 안에 있고 숫자가 0이고 방문하지 않았다면
				if (r >= 0 && c >= 0 && r < 16 && c < 16 && !visited[r][c]) {
					if (arr[r][c] == 3) {
						sb.append(1).append("\n");
						return;
					}
					visited[r][c] = true;
					que.offer(new location(r, c));
				}
			}
		}

		// 만약 while문이 다 돌때까지 return이 안걸렸다면 출구가 없는 것이므로 끝
		sb.append(0).append("\n");
	}// bfs
}
