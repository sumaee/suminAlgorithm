import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		boolean[][] check = new boolean[5][5]; // 빙고를 확인할 배열
		List<Integer> bingo = new ArrayList<>(); // 내 빙고 판
		List<Integer> num = new ArrayList<>();// 사회자가 부른 번호

		// 내 빙고판 채우기
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				bingo.add(Integer.parseInt(st.nextToken()));
			}
		}

		// 사회자 번호 채우기
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				num.add(Integer.parseInt(st.nextToken()));
			}
		}

		// 내 빙고판을 기준으로 사회자 번호와 맞는 부분 찾기
		for (int n = 0; n < num.size(); n++) {
			for (int b = 0; b < bingo.size(); b++) {
				if (bingo.get(b) == num.get(n)) {
					check[b / 5][b % 5] = true;

					if (n >= 5) {
						checkBingo(check);
						if (result >= 3) {
							System.out.println(n + 1);
							return;
						}
					}
				}
			}
		}

	}// main

	// 빙고 체크
	static void checkBingo(boolean[][] check) {
		result = 0;
		// 가로 세로 빙고 확인
		for (int i = 0; i < 5; i++) {
			int VCnt = 0; // 가로
			int HCnt = 0; // 세로
			for (int j = 0; j < 5; j++) {

				// 가로 확인
				if (check[i][j]) {
					VCnt++;
				} else {
					VCnt = 0;
				}

				// 세로 확인
				if (check[j][i]) {
					HCnt++;
				} else {
					HCnt = 0;
				}

				if (HCnt == 5) {
					result++;
				}
				if (VCnt == 5) {
					result++;
				}

			}
		}

		// 오른쪽 아래로 대각선확인
		for (int i = 0, j = 0; i < 5 && j < 5; i++, j++) {
			if (!check[i][j]) {
				break;
			}

			if (i == 4 && j == 4) {
				result++;
			}
		}

		// 왼쪽 아래로 대각선확인
		for (int i = 0, j = 4; i < 5 && j >= 0; i++, j--) {
			if (!check[i][j]) {
				break;
			}

			if (i == 4 && j == 0) {
				result++;
			}
		}

	}// checkBingo
}
