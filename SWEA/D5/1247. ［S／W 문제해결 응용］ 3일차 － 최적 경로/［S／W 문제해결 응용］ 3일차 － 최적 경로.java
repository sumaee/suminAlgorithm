import java.util.Scanner;

public class Solution {
	static int[][] client;
	static boolean[] visit;
	static int myHomeX;
	static int myHomeY;
	static int compX;
	static int compY;
	static int min;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int test_case = sc.nextInt();
		for (int tc = 1; tc <= test_case; tc++) {

			// 고객의 수
			N = sc.nextInt();
			visit = new boolean[N];
			min = Integer.MAX_VALUE;
			// 회사 좌표 받기
			compX = sc.nextInt();
			compY = sc.nextInt();

			// 내집 좌표 받기
			myHomeX = sc.nextInt();
			myHomeY = sc.nextInt();

			// 고객 좌표 받기
			// [][0]: x좌표
			// [][1]: y좌표
			client = new int[N][2];
			for (int i = 0; i < N; i++) {
				client[i][0] = sc.nextInt();
				client[i][1] = sc.nextInt();
			}

			// 회사에서 시작
			check(0, compX, compY, 0);
			sb.append("#" + tc + " " + min).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	public static void check(int idx, int x, int y, int dist) {

		// 지금 구하는 거리가 최솟값보다 크면 그냥 끝낼 것이다.
		if (dist > min) {
			return;
		}

		// 완성되었을 때 거리 확인
		if (idx == N) {
			// 마지막 고객 집에서 내집까지의 거리 더하기
			dist += (Math.abs(myHomeX - x) + Math.abs(y - myHomeY));

			// 최솟값 갱신
			min = Math.min(min, dist);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visit[i]) {

				visit[i] = true;
				check(idx + 1, client[i][0], client[i][1],
						dist + (Math.abs(x - client[i][0]) + Math.abs(y - client[i][1])));
				visit[i] = false;

			}

		}

	}// check
}
