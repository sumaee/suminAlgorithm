import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#" + tc + " ");
			int t = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());

			int result = pow(num, cnt);
			sb.append(result).append("\n");
		} // tc
		System.out.println(sb);
	}// main

	public static int pow(int num, int cnt) {
		if (cnt == 1) {
			return num;
		}
		// 지수가 홀수 인 경우
		if (cnt % 2 == 1) {
			int result = pow(num, (cnt - 1) / 2);
			return result * result * num;
		}

		// 지수가 짝수인 경우
		else {
			int result = pow(num, cnt / 2);
			return result * result;
		}
	}
}
