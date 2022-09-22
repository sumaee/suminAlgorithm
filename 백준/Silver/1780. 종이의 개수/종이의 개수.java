import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] arr;
	static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		result = new int[3];
		// 배열 채워넣기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		check(0, 0, N);
		for (int a : result) {
			System.out.println(a);
		}
	}// main

	static void check(int row, int col, int size) {
		if (check2(row, col, size)) {
			result[arr[row][col] + 1]++;
			return;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				check(row + (i * size) / 3, col + (j * size) / 3, size / 3);
			}
		}

	}

	static boolean check2(int row, int col, int size) {
		int onesum = arr[row][col];

		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (arr[i][j] != onesum) {
					return false;
				}
			}
		}
		return true;
	}
}
