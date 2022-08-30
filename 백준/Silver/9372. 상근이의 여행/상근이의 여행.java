import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static boolean[] check;
	static int[][] arr;
	static int N;
	static int M;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 국가의 수
			M = Integer.parseInt(st.nextToken()); // 비행기 종류
			arr = new int[N + 1][N + 1];
			check = new boolean[N + 1];
			cnt = 0;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				// a, b 두 위치를 비행기가 왕복
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				arr[a][b] = 1;
				arr[b][a] = 1;
			}

			checkDistance(1);
			sb.append(cnt).append("\n");

		} // tc
		System.out.println(sb);
	}// main

	static void checkDistance(int i) {
		check[i] = true;
		for (int j = 1; j <= N; j++) {
			if (arr[i][j] == 1 && check[j] == false) {
				cnt++;
				checkDistance(j);
			}
		}
	}

}
