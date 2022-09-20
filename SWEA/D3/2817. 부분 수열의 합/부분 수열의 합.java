import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int test_case = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken()); // 자연수 개수
			int K = Integer.parseInt(st.nextToken()); // 합
			int result = 0;
			int[] arr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < (1 << N); i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					if ((i & (1 << j)) > 0) {
						sum += arr[j];
					}
				}
				if (sum == K) {
					result++;
				}
			}

			sb.append(result).append("\n");
		} // tc
		System.out.println(sb);
	}// main
}