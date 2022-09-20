
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] arr;
	static int one;
	static int zero;
	static int minus;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		// 배열 채워넣기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		check(0, 0, N);
		System.out.println(minus);
		System.out.println(zero);
		System.out.println(one);
	}// main

	static void check(int row, int col, int size) {
		int onesum = 0;
		int zerosum = 0;
		int minussum = 0;

		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (arr[i][j] == 0) {
					zerosum++;
				} else if (arr[i][j] == 1) {
					onesum++;
				} else if (arr[i][j] == -1) {
					minussum++;
				}
			}
		}

		if (zerosum == (size * size)) {
			zero++;
			return;
		} else if (onesum == (size * size)) {
			one++;
			return;
		} else if (minussum == (size * size)) {
			minus++;
			return;
		} else {

			for (int i = row; i <= row + (2 * size) / 3; i += size / 3) {
				for (int j = col; j <= col + (2 * size) / 3; j += size / 3) {
					check(i, j, size / 3);
				}
			}
			return;

		}
	}
}
