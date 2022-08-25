import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			// 합을 구했을 때 나온 횟수를 저장할 배열
			int[] sumCnt = new int[N + M + 1];
			// 횟수의 크기를 비교할 변수
			int max = Integer.MIN_VALUE;

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					sumCnt[i + j]++;
					if (sumCnt[i + j] > max) {
						max = sumCnt[i + j];
					}
				}
			}

			for (int i = 0; i < sumCnt.length; i++) {
				if (sumCnt[i] == max)
					sb.append(i).append(" ");
			}
			sb.append("\n");
		} // tc
		System.out.println(sb);
	}// main
}
