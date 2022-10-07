import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] dp;
	static int[][] map;
	static int N, result;
	static final int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][(1 << N) - 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = dfs(0, 1);
		System.out.println(result);
	}// main

	private static int dfs(int curr, int nxt) {
		// 모든 도시 방문
		if (nxt == (1 << N) - 1) {

			if (map[curr][0] == 0) {
				return INF;
			}

			return map[curr][0];

		}

		if (dp[curr][nxt] != 0) {
			return dp[curr][nxt];
		}

		int min = INF;
		for (int i = 0; i < N; i++) {
			if ((nxt & (1 << i)) == 0 && map[curr][i] != 0) {
				min = Math.min(min, dfs(i, (nxt | (1 << i))) + map[curr][i]);
			}
		}

		return dp[curr][nxt] = min;
	}
}
