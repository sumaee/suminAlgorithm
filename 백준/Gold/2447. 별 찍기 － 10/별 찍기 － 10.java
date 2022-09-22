
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		arr = new char[N][N];

		star(0, 0, N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != '*') {
					sb.append(" ");
				} else {
					sb.append(arr[i][j]);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void star(int row, int col, int size) {

		if (size == 1) {
			// ***
			// * *
			// ***
			arr[row][col] = '*';
			return;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!(i == 1 && j == 1))
					star(row + (i * size) / 3, col + (j * size) / 3, size / 3);
			}
		}

	}
}
