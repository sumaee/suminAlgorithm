import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 블록의 최솟값과 최댓값 사이 확인
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 행
		int M = Integer.parseInt(st.nextToken()); // 열
		int B = Integer.parseInt(st.nextToken()); // 인벤토리
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		int[][] ground = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ground[i][j] = Integer.parseInt(st.nextToken());

				min = Math.min(ground[i][j], min);
				max = Math.max(ground[i][j], max);
			}
		}

		int maxground = 0;
		int result = 987654321;
		for (int i = min; i <= max; i++) {
			int time = 0;
			int blockCnt = B;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {

					// 더 높으면 블록 제거
					if (ground[r][c] > i) {
						time += ((ground[r][c] - i) * 2);
						blockCnt += (ground[r][c] - i);
					}
					// 낮으면 추가
					else {
						time += (i - ground[r][c]);
						blockCnt -= (i - ground[r][c]);
					}

				}
			}

			// 만약 위의 로직 수행하고 인벤토리의 블록이 양수면 가능하다는 것이므로 저장
			if (blockCnt >= 0) {
				if (time <= result) {
					result = time;
					maxground = i;
				}
			}
		}

		System.out.println(result + " " + maxground);

	}
}
