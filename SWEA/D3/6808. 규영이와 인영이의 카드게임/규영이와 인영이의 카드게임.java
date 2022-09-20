import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] ACard;
	static int[] BCard;
	static int[] temp;
	static boolean[] check;
	static boolean[] visit;
	static int win;
	static int lose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			ACard = new int[9]; // 상대방 카드
			check = new boolean[19];
			visit = new boolean[9];
			BCard = new int[9]; // 내 카드
			temp = new int[9]; // 임시 카드
			win = 0;
			lose = 0;
			st = new StringTokenizer(br.readLine());

			// 카드 채우기
			for (int i = 0; i < 9; i++) {
				ACard[i] = Integer.parseInt(st.nextToken());
				check[ACard[i]] = true;

			}

			int j = 0;
			for (int i = 1; i <= 18; i++) {
				if (!check[i]) {
					BCard[j++] = i;
				}
			}
			
			comb(0);
			sb.append(win + " ").append(lose).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	static void comb(int idx) {
		if (idx == 9) {
			int sumA = 0;
			int sumB = 0;
			for (int i = 0; i < 9; i++) {
				if (ACard[i] > temp[i]) {
					sumA += ACard[i] + temp[i];
				} else {
					sumB += ACard[i] + temp[i];
				}
			}

			if (sumA > sumB) {
				win++;
			} else if (sumB > sumA) {
				lose++;
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!visit[i]) {
				visit[i] = true;
				temp[idx] = BCard[i];
				comb(idx + 1);
				visit[i] = false;

			}
		}

	}// comb

}