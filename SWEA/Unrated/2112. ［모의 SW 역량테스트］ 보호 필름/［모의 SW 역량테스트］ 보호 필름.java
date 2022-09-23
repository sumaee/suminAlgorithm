import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int D; // 필름 두께
	static int W; // 필름 너비
	static int K; // 합격기준
	static int[][] film;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");

			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// 필름을 받을 배열 선언
			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			min = Integer.MAX_VALUE;
			change(0, 0);
			sb.append(min).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	public static void change(int row, int cnt) {
		// test가 true라면 끝냄
		if (test()) {
			min = Math.min(cnt, min);
			return;
		}

		// 검사하다가 약품투약 횟수가 최솟값보다 많아지면 더 볼필요 없음
		if (cnt > min) {
			return;
		}

		// 검사하는 행이 끝까지 도달하면 끝
		if (row == D) {
			return;
		}

		// 나중에 행 하나를 통으로 바꾸고 다시 되돌릴 때 사용하기 위함 임시 저장 배열
		int[] temp = new int[W];

		// 바꾸지 않고 그대로 가기
		for (int i = 0; i < W; i++) {
			temp[i] = film[row][i];
		}
		change(row + 1, cnt);

		// 해당줄을 A로 바꾸고 가기
		for (int i = 0; i < W; i++) {
			film[row][i] = 0;
		}
		change(row + 1, cnt+1);

		// 해당줄을 B로 바꾸고 가기
		for (int i = 0; i < W; i++) {
			film[row][i] = 1;
		}
		change(row + 1, cnt+1);

		// 바꿨던거 다시 원상복구
		for (int i = 0; i < W; i++) {
			film[row][i] = temp[i];
		}
	}

	public static boolean test() {
		// 열 기준으로 배열을 돌며 테스트 확인
		for (int j = 0; j < W; j++) {
			int count = 1;
			boolean check = false;
			for (int i = 0; i < D - 1; i++) {
				// 현재필름과 다음필름을 비교해서 같으면 count ++;
				if (film[i][j] == film[i + 1][j]) {
					count++;
				}else {
					count = 1;
				}

				if (count == K) {
					check = true;
					break;
				}
			}

			// 하나의 열을 다 돌았는데 check가 그대로 false라는 것은 K개의 연속되는 것이 없다는 것
			if (!check) {
				return false;
			}
		}

		// 배열을 다 돌고 나왔다는 것은 테스트 통과했다는 뜻
		return true;

	}// test
}
