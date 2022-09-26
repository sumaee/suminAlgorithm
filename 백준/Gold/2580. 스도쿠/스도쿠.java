import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr = new int[9][9];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 스도쿠 배열 채우기
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		check(0, 0);
	}// main

	public static void check(int row, int col) {
		// 만약 한줄을 다 돌렸다면 다음 줄로 넘기기
		if (col == 9) {
			check(row + 1, 0);
			return;
		}
		
		// 만약 마지막 줄까지 다 갔다면 될수 있는 수를 다 채우고 왔다는 것이므로 출력
		if (row == 9) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(arr[i][j] + " ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}
		
		// 해당 수가 0이라면 채워넣고 가능한지 확인 후 다음단계 넘어가든 말든 정하기
		if (arr[row][col] == 0) {
			for (int k = 1; k <= 9; k++) {
				if (isPossible(row, col, k)) {
					arr[row][col] = k;

					// 다음 넘어가서 빈칸인지 확인
					check(row, col + 1);
				}
			}

			// 만약 9까지 다 돌았는데 들어가는게 없으면
			// 안되는 것이므로 그냥 0으로 바꾸고 끝내기
			arr[row][col] = 0;
			return;
		}

		// 빈칸이 아니면 그냥 넘기기
		check(row, col + 1);
	}

	public static boolean isPossible(int row, int col, int num) {
		// 스도쿠를 돌며 해당 숫자가 없으면 가능한 걸로 판단
		// 가로, 세로 검사
		for (int i = 0; i < 9; i++) {

			if (arr[i][col] == num || arr[row][i] == num) {
				return false;
			}
		}

		// 사각형 검사
		// r, c : 빈칸이 있는 곳의 가장 왼쪽 위 지점

		int r = row / 3 * 3;
		int c = col / 3 * 3;
		for (int i = r; i < r + 3; i++) {
			for (int j = c; j < c + 3; j++) {
				if (arr[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}
}
