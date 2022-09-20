import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] paper;
	static int oneSum;
	static int zeroSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 색종이 한변의 수
		paper = new int[N][N];

		// 종이 채우기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		check(0, 0, N);
		System.out.println(zeroSum);
		System.out.println(oneSum);
	}// main

	static void check(int srow, int scol, int size) {
		int zero = 0;
		int one = 0;

		for (int i = srow; i < srow + size; i++) {
			for (int j = scol; j < scol + size; j++) {
				if (paper[i][j] == 1) {
					one++;
				} else {
					zero++;
				}
			}
		}

		if (one == (size * size)) {
			oneSum++;
		} else if (zero == (size * size)) {
			zeroSum++;
		} else if (size > 0) {
			check(srow, scol, size / 2);
			check(srow, scol + size / 2, size / 2);
			check(srow + size / 2, scol, size / 2);
			check(srow + size / 2, scol + size / 2, size / 2);
			return;

		}
	}

}
