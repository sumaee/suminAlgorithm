import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] value;
	static int[] year;
	static int minValue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			value = new int[4];
			year = new int[13];
			// 가격 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				value[i] = Integer.parseInt(st.nextToken());
			}

			// 1년 사용권을 일단 최저값으로 두기
			minValue = value[3];

			// 월별 이용 예정 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				year[i] = Integer.parseInt(st.nextToken());
			}

			dfs(1, 0);
			sb.append(minValue).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	public static void dfs(int month, int sum) {

		if (sum >= minValue) {
			return;
		}
		if (month >= 13) {
			minValue = Math.min(minValue, sum);
			return;
		}

		// 이용안하면 안사
		if (year[month] == 0) {
			dfs(month + 1, sum);
		}

		if (year[month] > 0) {
			// 1일 이용권으로 사
			dfs(month + 1, sum + value[0] * year[month]);
			// 한달 이용권으로 사
			dfs(month + 1, sum + value[1]);

			// 만약 10월전이라면 3개월 이용권 사
			dfs(month + 3, sum + value[2]);
		}
	}
}
