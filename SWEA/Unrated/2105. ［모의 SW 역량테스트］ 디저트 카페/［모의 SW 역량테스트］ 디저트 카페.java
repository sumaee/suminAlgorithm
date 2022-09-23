
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] cafe;
	static boolean[] eat; // 해당 디저트를 먹었는지 확인하기 위한 배열
	static int max;
	static int[] dr = { -1, -1, 1, 1 };
	static int[] dc = { -1, 1, 1, -1 };
	static int[] temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(br.readLine()); // 지역 크기
			cafe = new int[N][N];
			eat = new boolean[101];
			temp = new int[2];
			// 카페 정보 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cafe[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					eat[cafe[i][j]] = true;
					temp[0] = i;
					temp[1] = j;
					check(i, j, 1, 0);
					eat[cafe[i][j]] = false;
				}
			}

			sb.append(max < 4 ? -1 : max).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	public static void check(int row, int col, int count, int dir) {

		// 기저
		if (dir == 4) {
			return;
		}

		int r = row + dr[dir];
		int c = col + dc[dir];

		if (dir == 3 && r == temp[0] && c == temp[1]) {
			max = Math.max(max, count);
			return;
		}

		if (r >= 0 && c >= 0 && r < N && c < N && !eat[cafe[r][c]]) {
			eat[cafe[r][c]] = true;
			check(r, c, count + 1, dir);
			check(r, c, count + 1, dir + 1);
			eat[cafe[r][c]] = false;
		}
	}// check
}
