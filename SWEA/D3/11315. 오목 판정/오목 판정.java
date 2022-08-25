import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());

			// 오목판 생성
			String[] arr = new String[N];
			char[][] omok = new char[N][N];
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine();
				for (int j = 0; j < N; j++) {
					omok[i][j] = arr[i].charAt(j);
				}
			} // 오목판 생성 끝

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(omok[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			// 오목 확인
			// 8방 범위 5칸 까지 확인
			// X가 있을 때마다 cnt++
			// cnt가 5라면 YES 출력 후 끝
			// 배열 끝까지 없다면 NO를 출력하고 끝
			boolean check = false;
			out: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (omok[i][j] == 'o') {

						// 세로 오목 확인
						int cnt = 1;
						for (int row = i + 1; row < N; row++) {
							if (omok[row][j] != 'o') {
								check = false;
								break;
							}
							cnt++;
							if (cnt == 5) {
								check = true;
								sb.append("YES").append("\n");
								break out;
							}
						}

						// 세로 오목 확인
						cnt = 1;
						for (int col = j + 1; col < N; col++) {
							if (omok[i][col] != 'o') {
								check = false;
								break;
							}
							cnt++;
							if (cnt == 5) {
								check = true;
								sb.append("YES").append("\n");
								break out;
							}
						}

						// 오른쪽 아래 대각선 확인
						cnt = 1;
						for (int row = i + 1, col = j + 1; row < N && col < N; row++, col++) {
							if (omok[row][col] != 'o') {
								check = false;
								break;
							}
							cnt++;
							if (cnt == 5) {
								check = true;
								sb.append("YES").append("\n");
								break out;
							}
						}

						// 왼쪽 아래 대각선 확인
						cnt = 1;
						for (int row = i + 1, col = j - 1; row < N && col >= 0; row++, col--) {
							if (omok[row][col] != 'o') {
								check = false;
								break;
							}
							cnt++;
							if (cnt == 5) {
								check = true;
								sb.append("YES").append("\n");
								break out;
							}
						}
					}

				}
			}
			if (!check)
				sb.append("NO").append("\n");

		} // tc
		System.out.println(sb);
	}// main
}
