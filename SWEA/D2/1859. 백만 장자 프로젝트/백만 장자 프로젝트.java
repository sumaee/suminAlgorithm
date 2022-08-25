import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] price;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");

			N = Integer.parseInt(br.readLine()); // N일
			price = new int[N];
			st = new StringTokenizer(br.readLine());
			int maxidx = 0;
			for (int i = 0; i < N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
				if (price[i] > price[maxidx]) {
					maxidx = i;
				}
			}

			long sum = 0;
			int maxIdx = 0, max = 1;
			int start = 0;
			while (maxIdx < N - 1) {
				for (int i = start; i < N; i++) {
					if (price[i] >= max) {
						maxIdx = i;
						max = price[i];
					}
				}

				for (int i = start; i < maxIdx; i++) {
					sum += price[maxIdx] - price[i];
				}
				start=maxIdx+1;
				max=-1;
			}
			sb.append(sum).append("\n");
		} // tc
		System.out.println(sb);
	}// main

}
