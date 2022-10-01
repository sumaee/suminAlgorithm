import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 987654321;
	static int[][] city;
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 도시 수
		n = Integer.parseInt(br.readLine());

		city = new int[n + 1][n + 1];

		// 초기 설정
		for (int i = 1; i <= n; i++) {
			Arrays.fill(city[i], INF);
			city[i][i] = 0;
		}

		// 버스의 개수
		m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int stcity = Integer.parseInt(st.nextToken());
			int edcity = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			// 들어오는 가중치중 가장 작은 값을 설정
			city[stcity][edcity] = Math.min(value, city[stcity][edcity]);
		}

		findMin();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {

				sb.append(city[i][j] == INF ? 0 + " " : city[i][j] + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}// main

	private static void findMin() {
		// 경유
		for (int i = 1; i <= n; i++) {
			// 출발
			for (int j = 1; j <= n; j++) {
				// 출발지와 경유지가 같지 않을 때 진행
				if (i != j) {
					// 도착
					for (int k = 1; k <= n; k++) {
						// 출발지랑 도착랑 같지 않고, 경유지가 도착지랑 같지 않을 때
						if (j != k && i != k) {
							// 기존의 비용이 경유한 값보다 비싸면 갱신
							if (city[j][k] > city[j][i] + city[i][k]) {
								city[j][k] = city[j][i] + city[i][k];
							}
						}
					}
				}
			}
		}
	}
}
