import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static Long[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new Long[N + 1][10];

		for (int i = 0; i < 10; i++) {
			arr[1][i] = 1L;
		}

		long result = 0;
		for (int i = 1; i < 10; i++) {
			result += check(N, i);
		}

		System.out.println(result % 1000000000);

	}// main

	static long check(int row, int col) {

		if (row == 1) {
			return arr[row][col] % 1000000000;
		}

		if (arr[row][col] == null) {
			if (col == 0) {
				arr[row][col] = check(row - 1, 1);
			}

			else if (col == 9) {
				arr[row][col] = check(row - 1, 8);
			}

			else {
				arr[row][col] = check(row - 1, col - 1) + check(row - 1, col + 1);
			}
		}
		return arr[row][col]% 1000000000;

	}
}
