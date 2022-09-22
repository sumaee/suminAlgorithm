import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static boolean[][] board;
	static int N;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			ans = 0;
			N = Integer.parseInt(br.readLine());
			board = new boolean[N][N];
			put(0);
			sb.append(ans).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	public static void put(int row) {
		if (row == N) {
			ans++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (possible(row, i)) {
				board[row][i] = true;
				put(row + 1);
				board[row][i] = false;
			}
		}

	}// check

	public static boolean possible(int row, int col) {
		int left = col;
		int right = col;

		for (int i = row - 1; i >= 0; i--) {
			left--;
			right++;

			if (left >= 0 && board[i][left]) {
				return false;
			}
			if (right < N && board[i][right]) {
				return false;
			}
			if (board[i][col]) {
				return false;
			}

		}

		return true;
	}// possible
}
