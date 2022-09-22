import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] myHome;
	static int[] comp;
	static int[][] client;
	static boolean[] visit;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());// 고객의 수

			st = new StringTokenizer(br.readLine());
			// [0] : 회사 x좌표
			// [1] : 회사 y좌표
			comp = new int[2];
			comp[0] = Integer.parseInt(st.nextToken());
			comp[1] = Integer.parseInt(st.nextToken());

			// [0] : 내집 x좌표
			// [1] : 내집 y좌표
			myHome = new int[2];
			myHome[0] = Integer.parseInt(st.nextToken());
			myHome[1] = Integer.parseInt(st.nextToken());

			// [][0] : 고객 x좌표
			// [][1] : 고객 y좌표
			client = new int[N][2];
			visit = new boolean[N];
			for (int i = 0; i < N; i++) {
				client[i][0] = Integer.parseInt(st.nextToken());
				client[i][1] = Integer.parseInt(st.nextToken());
			}

			findMin(0, comp[0], comp[1], 0);
			sb.append(min).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	public static void findMin(int idx, int x, int y, int dis) {
		// 구하다가 거리가 지금의 최솟값보다 커지면 더 이상 볼 필요가 없으므로 끝내버림
		if (dis >= min) {
			return;
		}

		// 마지막 고객집 까지 갔다면 내집까지 거리 계산
		if (idx == N) {
			dis += Math.abs(x - myHome[0]) + Math.abs(y - myHome[1]);
			min = Math.min(min, dis);
			return;
		}

		for (int i = 0; i < N; i++) {
			// 방문하지 않은 고객의 집이라면 진행
			if (!visit[i]) {
				visit[i] = true;
				findMin(idx + 1, client[i][0], client[i][1],
						dis + Math.abs(x - client[i][0]) + Math.abs(y - client[i][1]));
				visit[i] = false;
			}
		}
	}
}
